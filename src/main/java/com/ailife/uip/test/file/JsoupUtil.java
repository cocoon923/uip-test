package com.ailife.uip.test.file;

import com.ailife.uip.test.config.DocProperties;
import com.ailife.uip.test.file.entity.AiCrmTree;
import com.ailife.uip.test.file.entity.Inter;
import com.ailife.uip.test.file.entity.Param;
import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by chenmm on 9/25/2014.
 */
@Component
public class JsoupUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static String MAGIC = "@@@";

	private List<Inter> interList = new ArrayList<Inter>();

	public List<Inter> getInterList() {
		return interList;
	}

	@Autowired
	private DocProperties docProperties;

	public void parseHtml(String html) {
		String[] h1Fragments = getFragments(html, HTMLLEVEL.h1);

		Map<String, String> nodesAndClasses = getNodesAndClasses(Jsoup.parseBodyFragment(h1Fragments[4]));
		String[] h2Fragments = getFragments(h1Fragments[3], HTMLLEVEL.h2);
		Iterator<Map.Entry<String, String>> entryIterator = nodesAndClasses.entrySet().iterator();
		for (int i = 1; i < h2Fragments.length; i++) {
			Map.Entry<String, String> entry = entryIterator.next();
			String[] h3Fragments = getFragments(h2Fragments[i], HTMLLEVEL.h3);
			for (int j = 1; j < h3Fragments.length; j++) {
				Inter inter = new Inter();
				Document interDocument = Jsoup.parseBodyFragment(h3Fragments[j]);
				Element titleElement = getElement(interDocument, HTMLLEVEL.h3);
				inter.setName(titleElement.text().trim());
				inter.setImplClass(entry.getValue());
				inter.setInterfaceCode(docProperties.getCode());
				String seq = getInterSeq(i, j);
				inter.setServiceSeq(Long.parseLong(seq));
				Elements descElementTrs = getNextElement(titleElement, HTMLLEVEL.table).child(0).children();
				inter.setDesc(descElementTrs.get(1).child(1).text());
				inter.setBusiCode(descElementTrs.get(6).child(1).text());
				Element reqParams = getNextElement(getElement(interDocument, HTMLLEVEL.h4, "输入参数"), HTMLLEVEL.table);
				Element respParams = getNextElement(getElement(interDocument, HTMLLEVEL.h4, "输出参数"), HTMLLEVEL.table);
				long paramSeq = Long.parseLong(seq + "000");
				inter.addParams(new ParamUtil(paramSeq, reqParams, true).getParams());
				inter.addParams(new ParamUtil(paramSeq, respParams, false).getParams());
				interList.add(inter);
			}
		}
	}


	private String getInterSeq(int category, int inter) {
		return category + Strings.padStart(String.valueOf(inter), 6, '0');
	}

	private String[] getFragments(String html, HTMLLEVEL htmllevel) {
		return enchant(html, htmllevel).split(MAGIC);
	}

	private String enchant(String html, HTMLLEVEL htmlLevel) {
		return html.replaceAll(htmlLevel.getBeginTag(), MAGIC + htmlLevel.getBeginTag());
	}

	private Map<String, String> getNodesAndClasses(Document document) {
		Map<String, String> nodesAndClasses = new LinkedHashMap<String, String>();
		String packagePath = getElement(document, HTMLLEVEL.h3, "接口映射包名").nextElementSibling().text() + ".";
		Element tableElement = getElement(document, HTMLLEVEL.h3, "映射关系").nextElementSibling();
		Elements trsWithTh = tableElement.child(0).children();
		List<Element> trs = trsWithTh.subList(1, trsWithTh.size());
		for (Element tr : trs) {
			String key = tr.child(0).text();
			String value = packagePath + tr.child(1).text();
			nodesAndClasses.put(key, value);
			logger.info("Load Node and Class: <" + key + ", " + value);
		}
		return nodesAndClasses;
	}

	private Element getNextElement(Element element, HTMLLEVEL htmllevel) {
		if (htmllevel.toString().equals(element.tagName())) {
			return element;
		} else {
			return getNextElement(element.nextElementSibling(), htmllevel);
		}
	}

	private Element getElement(Document document, HTMLLEVEL htmllevel) {
		Elements elements = document.select(htmllevel.toString());
		if (elements.isEmpty()) {
			return null;
		} else {
			return elements.get(0);
		}
	}

	private Element getElement(Document document, HTMLLEVEL htmllevel, String content) {
		Elements elements = document.select(htmllevel + ":contains(" + content + ")");
		if (elements.isEmpty()) {
			return null;
		} else {
			return elements.get(0);
		}
	}

	class ParamUtil {
		Element element;
		boolean isReq;
		private long paramSeq;

		private int paramCount = 1;

		ParamUtil(long paramSeq, Element element, boolean isReq) {
			this.paramSeq = paramSeq;
			this.element = element;
			this.isReq = isReq;
		}

		List<Param> getParams() {
			List<Param> list = new ArrayList<Param>();
			list.addAll(dealWithTable());
			paramCount = 1;
			return list;
		}

		private List<Param> dealWithTable() {
			List<Param> list = new ArrayList<Param>();
			if (element != null) {
				Elements trs = element.child(0).children();


				AiCrmTree root = new AiCrmTree();
				Map<String, AiCrmTree> paramTemp = new HashMap<String, AiCrmTree>();
				if (isReq) {
					AiCrmTree body = new AiCrmTree("RequestBody");
					body.setIsNull("1");
					paramTemp.put("RequestBody", body);
					root.addChild(body);
				} else {
					AiCrmTree body = new AiCrmTree("ResponseBody");
					paramTemp.put("ResponseBody", body);
					body.setIsNull("1");
					root.addChild(body);
				}
				//序号	参数名称		描述		约束		取值		备注
				for (int i = 1, trSize = trs.size(); i < trSize; i++) {
					Element atr = trs.get(i);
					String parentcode = atr.child(0).text().trim();
					if (StringUtils.isBlank(parentcode)) {
						break;
					}
					String code = atr.child(1).text().trim();
					String cons = atr.child(2).text().trim();
					String paramType = atr.child(3).text().trim();
					String name = atr.child(5).text().trim();

					AiCrmTree node = new AiCrmTree(code);
					AiCrmTree parent = paramTemp.get(parentcode);
					if (cons.equals("*")) {
						node.setCons(true);
						node.setIsNull("0");
					} else if (cons.equals("+")) {
						node.setCons(true);
						node.setIsNull("1");
					} else if (cons.equals("1")) {
						node.setCons(false);
						if (StringUtils.isNotBlank(parent.getIsNull()) && "1".equals(parent.getIsNull())) {
							node.setIsNull("1");
						} else {
							node.setIsNull("0");
						}
					} else {
						node.setCons(false);
						node.setIsNull("0");
					}
					if (paramType.equalsIgnoreCase("String") || paramType.equalsIgnoreCase("Number") || paramType.equalsIgnoreCase("Date")) {
						node.setName(name);
					} else {
						paramTemp.put(code, node);
					}
					parent.addChild(node);
				}

				List<String> params = AiCrmTree.getPathTree(root);
				for (String paramExpr : params) {
					String[] values = paramExpr.split(">>");
					Param param = new Param();
					param.setParamCode(values[0]);
//					param.setParamValue(values[0]);
					param.setParamName(values[1]);
					param.setParamType(isReq ? "0" : "1");
					param.setIsNull(StringUtils.isBlank(values[2]) || "0".equals(values[2]) ? "1" : "0");
					param.setSort(paramCount++);
					param.setSeq(paramSeq++);
					list.add(param);
				}
			}

			return list;
		}


		private String[] pubInfos = {"RequestHead.AppKey_AppKey", "RequestHead.BusiCode_API接口名称", "RequestHead.TransactionID_交易流水号",
				"RequestHead.ReqTime_请求时间", "RequestHead.Sign_签名字符串", "RequestHead.AccessToken_AccessToken", "RequestHead.Version_接口版本",
				"RequestHead.DstSysID_落地方系统", "RequestHead.TenantId_租户id", "RequestHead.AcceptRegionCode_受理地市", "RequestHead.ChargeFlag_一次性费用",
				"RequestHead.AcceptChannelType_受理渠道类型", "RequestHead.AcceptChannelCode_受理渠道", "RequestHead.NotifyFlag_是否发送通知",
				"RequestHead.AcceptStaffId_受理员工"};
		private String[] errorInfos = {"ResponseHead.RspCode_会话应答代码", "ResponseHead.RspDesc_会话应答描述", "ResponseHead.RspTime_应答时间",
				"ResponseHead.RspTransactionID_应答流水号", "ResponseHead.RspType_会话应答类型", "ResponseHead.TransactionID_交易流水号"};
	}

}
