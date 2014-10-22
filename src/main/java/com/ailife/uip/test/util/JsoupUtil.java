package com.ailife.uip.test.util;

import com.ailife.uip.test.db.entity.Param;
import com.ailife.uip.test.db.util.IdGenerator;
import com.ailife.uip.test.db.util.StaticDataUtil;
import com.ailife.uip.test.file.entity.Inter;
import com.google.common.base.CaseFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by chenmm on 10/21/2014.
 */
public class JsoupUtil {

	private final static String MAGIC = "@@@";

	public static List<Inter> parseHtml(String html) {
		List<Inter> interList = new ArrayList<Inter>();
		String[] h1Fragments = getFragments(html, HTMLLEVEL.h1);

		Map<String, String> nodesAndClasses = getNodesAndClasses(Jsoup.parseBodyFragment(h1Fragments[4]));
		String[] h2Fragments = getFragments(h1Fragments[3], HTMLLEVEL.h2);
		Iterator<Map.Entry<String, String>> entryIterator = nodesAndClasses.entrySet().iterator();
		for (int i = 1; i < h2Fragments.length; i++) {
			Map.Entry<String, String> entry = entryIterator.next();
			String[] h3Fragments = getFragments(h2Fragments[i], HTMLLEVEL.h3);
			for (int j = 1; j < h3Fragments.length; j++) {
				Inter inter = new Inter();
				inter.setSeq(IdGenerator.getNewId());
				Document interDocument = Jsoup.parseBodyFragment(h3Fragments[j]);
				Element titleElement = getElement(interDocument, HTMLLEVEL.h3);
				inter.setName(titleElement.text().trim());
				inter.setImplClass(entry.getValue());
				Elements descElementTrs = getNextElement(titleElement, HTMLLEVEL.table).child(0).children();
				inter.setDesc(descElementTrs.get(1).child(1).text());
				String busiCode = descElementTrs.get(6).child(1).text();
				inter.setBusiCode(busiCode);
				inter.setInvokeMethod(StringUtils.caseFormatTransfer(CaseFormat.UPPER_CAMEL, CaseFormat.LOWER_CAMEL, busiCode));
				inter.setSort(j);
				Element reqParams = getNextElement(getElement(interDocument, HTMLLEVEL.h4, "输入参数"), HTMLLEVEL.table);
				Element respParams = getNextElement(getElement(interDocument, HTMLLEVEL.h4, "输出参数"), HTMLLEVEL.table);
				inter.addParams(getParamsFromTable(reqParams, true));
				inter.addParams(getParamsFromTable(respParams, false));
				LogUtil.debug(JsoupUtil.class, "Load Inter: " + inter + Symbol.CRLF);
				interList.add(inter);
			}
		}
		return interList;
	}

	private static List<Param> getParamsFromTable(Element element, boolean isReq) {
		List<Param> params = new ArrayList<Param>();
		Param previousParam = isReq ? StaticDataUtil.getReqRootParam() : StaticDataUtil.getRespRootParam();
		String paramTypeName = isReq ? "REQUEST" : "RESPONSE";
		String paramType = StaticDataUtil.getStaticDataValue(DATATYPE.PARAM_TYPE.toString(), paramTypeName);
		params.add(previousParam);
		Stack<Param> stack = new Stack<Param>();
		stack.push(previousParam);
		if (element != null) {
			Elements trs = element.child(0).children();
			for (int i = 1; i < trs.size(); i++) {



				Element tr = trs.get(i);
				String parentParamCode = tr.child(0).text().trim();
				String paramCode = tr.child(1).text().trim();
				String paramTimes = tr.child(2).text().trim();
				String paramClazz = tr.child(3).text().trim();
				String paramLength = tr.child(4).text().trim();
				String paramName = tr.child(5).text().trim();
				String remark = tr.child(6).text().trim();

				if(StringUtils.isNullorEmpty(parentParamCode)){
					break;
				}

				if (StringUtils.isNullorEmpty(paramName)) {
					paramName = paramCode;
				}
				if (!StaticDataUtil.getStaticData(DATATYPE.PARAM_CLAZZ.toString()).values().contains(paramClazz)) {
					paramClazz = "";
				}
				//TODO doValidate();

				Param param = new Param();
				param.setSeq(IdGenerator.getNewId());
				param.setParamName(paramName);
				param.setParamCode(paramCode);
				param.setParamClazz(paramClazz);
				param.setParamLength(paramLength);
				param.setParamTimes(paramTimes);
				param.setParamType(paramType);
				param.setRemark(remark);

				if (parentParamCode.equals(previousParam.getParamCode())) {
					param.setSort(1);
					param.setParentSeq(previousParam.getSeq());
					LogUtil.debug(JsoupUtil.class, "Load Param: " + param);
					params.add(param);

					stack.push(previousParam);
					previousParam = param;
				} else if (parentParamCode.equals(stack.peek().getParamCode())) {
					param.setSort(previousParam.getSort() + 1);
					param.setParentSeq(stack.peek().getSeq());
					LogUtil.debug(JsoupUtil.class, "Load Param: " + param);
					params.add(param);

					previousParam = param;
				} else {
					while (!stack.isEmpty()) {
						previousParam = stack.pop();

						Param peek = stack.peek();
						if (parentParamCode.equals(peek.getParamCode())) {
							param.setSort(previousParam.getSort() + 1);
							param.setParentSeq(peek.getSeq());
							LogUtil.debug(JsoupUtil.class, "Load Param: " + param);
							params.add(param);

							previousParam = param;
							break;
						}
					}
				}
			}
		}
		return params;
	}

	private static String[] getFragments(String html, HTMLLEVEL htmllevel) {
		return enchant(html, htmllevel).split(MAGIC);
	}

	private static String enchant(String html, HTMLLEVEL htmlLevel) {
		return html.replaceAll(htmlLevel.getBeginTag(), MAGIC + htmlLevel.getBeginTag());
	}

	private static Map<String, String> getNodesAndClasses(Document document) {
		Map<String, String> nodesAndClasses = new LinkedHashMap<String, String>();
		String packagePath = getElement(document, HTMLLEVEL.h3, "接口映射包名").nextElementSibling().text() + ".";
		Element tableElement = getElement(document, HTMLLEVEL.h3, "映射关系").nextElementSibling();
		Elements trsWithTh = tableElement.child(0).children();
		List<Element> trs = trsWithTh.subList(1, trsWithTh.size());
		for (Element tr : trs) {
			String key = tr.child(0).text();
			String value = packagePath + tr.child(1).text();
			nodesAndClasses.put(key, value);
			LogUtil.info(JsoupUtil.class, "Load Node and Class: <" + key + ", " + value);
		}
		return nodesAndClasses;
	}

	private static Element getNextElement(Element element, HTMLLEVEL htmllevel) {
		if (htmllevel.toString().equals(element.tagName())) {
			return element;
		} else {
			return getNextElement(element.nextElementSibling(), htmllevel);
		}
	}

	private static Element getElement(Document document, HTMLLEVEL htmllevel) {
		Elements elements = document.select(htmllevel.toString());
		if (elements.isEmpty()) {
			return null;
		} else {
			return elements.get(0);
		}
	}

	private static Element getElement(Document document, HTMLLEVEL htmllevel, String content) {
		Elements elements = document.select(htmllevel + ":contains(" + content + ")");
		if (elements.isEmpty()) {
			return null;
		} else {
			return elements.get(0);
		}
	}

}
