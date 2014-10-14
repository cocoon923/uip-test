package com.ailife.uip.test.file.entity;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.devil.bean.BOBsBusiAccessParamBean;

public class AiCrmTree {

	private static String ROM_STR = "abcdefghijklmnopqrstuvwxyz0123456789";

	private boolean isLeaf;
	private List<AiCrmTree> children;
	private String value;
	// 约束 false 表0/1  true 表 +/*
	private boolean cons;
	private boolean root;
	private AiCrmTree parnetNode;
	private Map attrMap = new HashMap();
	private String id;
	private String name;
	private String isNull;


	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AiCrmTree() {
		this.value = "AICRMSERVICE";
		this.isLeaf = true;
		this.children = new ArrayList<AiCrmTree>();
		this.cons = false;
		this.root = true;
		this.id = getRomId(6);
	}

	public AiCrmTree(String nodeValue) {
		this.value = nodeValue;
		this.isLeaf = true;
		this.cons = false;
		this.children = new ArrayList<AiCrmTree>();
		this.root = true;
		this.id = getRomId(6);
	}

	public AiCrmTree(String nodeValue, boolean cons) {
		this.value = nodeValue;
		this.isLeaf = true;
		this.cons = cons;
		this.children = new ArrayList<AiCrmTree>();
		this.root = false;
		this.id = getRomId(6);
	}

	public String getId() {
		return this.id;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<AiCrmTree> getChildren() {
		return children;
	}

	public void setChildren(List<AiCrmTree> children) {
		this.children = children;
	}

	public void set(String key, Object value) {
		attrMap.put(key, value);
	}

	public Object get(String key) {
		return attrMap.get(key);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isCons() {
		return cons;
	}

	public void setCons(boolean cons) {
		this.cons = cons;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public AiCrmTree getParnetNode() {
		return parnetNode;
	}

	public void setParnetNode(AiCrmTree parnetNode) {
		this.parnetNode = parnetNode;
	}

	public boolean isLeaf(AiCrmTree node) {
		return node.isLeaf && node.children.size() == 0;
	}

	public AiCrmTree addChild(AiCrmTree node) {
		this.children.add(node);
		this.isLeaf = false;
		node.setRoot(false);
		return this;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.value);
		if (this.cons)
			sb.append("[n]");
		sb.append(" : ");
		sb.append(children.toString());
		if (!this.isRoot()) {
			sb.append(" parent :" + this.getParnetNode().value);
		}
		return sb.toString();
	}

	/**
	 * 遍历节点
	 *
	 * @param node
	 * @param path
	 * @param items
	 * @return
	 */
	private static List<String> searchTree(AiCrmTree node, String path, List<String> items) {
		if (null == items) {
			items = new ArrayList<String>();
		}
		if (node.isLeaf && null != path) {
			items.add(path + "." + node.getNodeExpr(node) + ">>" + node.getName() + ">>" + node.getIsNull());
		} else if (!node.isLeaf) {
			List<AiCrmTree> childs = node.getChildren();
			if (null != path)
				path = path + ".";
			else
				path = "";
			path = path + node.getNodeExpr(node);
			for (int i = 0; i < childs.size(); i++) {
				items = searchTree(childs.get(i), path, items);
			}
		}
		return items;
	}

	/**
	 * 遍历树
	 *
	 * @param root
	 * @return
	 */
	public static List<String> getPathTree(AiCrmTree root) {
		if (!root.isRoot()) {
			return new ArrayList<String>();
		}
		List<AiCrmTree> children = root.getChildren();
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < children.size(); i++) {
			items = searchTree(children.get(i), null, items);
		}
		return items;
	}

	public String getNodeExpr(AiCrmTree node) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.value);
		if (this.cons)
			sb.append("[n]");
		return sb.toString();
	}

	public boolean addChildByPath(String path) {
		if (StringUtils.isBlank(path)) {
			return false;
		}
		String[] nodeNames = path.split("\\.");
		AiCrmTree node = this;
		boolean flag = false;
		for (int i = 0; i < nodeNames.length; i++) {
			flag = false;
			boolean nodeCons = nodeNames[i].endsWith("[n]");
			String value = nodeNames[i].replaceAll("\\[n\\]", "");
			List<AiCrmTree> list = node.getChildren();
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).value.equals(value) && list.get(j).cons == nodeCons) {
					node = list.get(j);
					flag = true;
					break;
				}
			}
			if (!flag) {
				AiCrmTree newNode = new AiCrmTree(value, nodeCons);
				newNode.setParnetNode(node);
				node.addChild(newNode);
				node = newNode;
			}
		}
		return true;
	}

	public static Map returnMap(AiCrmTree root) {
		Map map = new HashMap();
		List<AiCrmTree> children = root.getChildren();
		for (int i = 0; i < children.size(); i++) {
			AiCrmTree node = children.get(i);
			if (node.isLeaf()) {
				if (!node.isCons()) {
					map.put(node.getValue(), "");
				} else {
					List listMap = new ArrayList();
					listMap.add("");
					map.put(node.getValue(), listMap);
				}
			} else if (!node.isCons()) {
				map.put(node.getValue(), AiCrmTree.returnMap(node));
			} else {
				List list = new ArrayList();
				Map listMap = AiCrmTree.returnMap(node);
				list.add(listMap);
				map.put(node.getValue(), list);
			}
		}

		return map;
	}

	private static String getRomId(int length) {
		String idRnd = "";
		for (int i = 1; i <= length; i++) {
			int r = (int) Math.round(Math.random() * (35 - 0));
			idRnd += ROM_STR.substring(r, r + 1);
		}
		return idRnd;
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(Arrays.toString("PubInfo.InterfaceType".split("\\.")));
//		System.out.println("PubInfo.InterfaceType[n]".endsWith("[n]"));

//		boolean a = true;
//		boolean b = true;
//		System.out.println(a==b);

//		AiCrmTree root  = new AiCrmTree();
//		AiCrmTree pubNode  = new AiCrmTree("PubInfo");
//		root.addChild(pubNode);
//		AiCrmTree transId = new AiCrmTree("TransactionId");
//		AiCrmTree infId = new AiCrmTree("InterfaceId");
//		AiCrmTree infType = new AiCrmTree("InterfaceType");
//		AiCrmTree opId = new AiCrmTree("OpId");
//		pubNode.addChild(transId);
//		pubNode.addChild(infId);
//		pubNode.addChild(infType);
//		pubNode.addChild(opId);
//		AiCrmTree request = new AiCrmTree("Request");
//		root.addChild(request);
//		AiCrmTree busiParam = new AiCrmTree("BusiParams");
//		request.addChild(busiParam);
//		AiCrmTree busiCode = new AiCrmTree("BusiCode");
//		request.addChild(busiCode);
//		AiCrmTree serviceNum = new AiCrmTree("ServiceNum",true);
//		busiParam.addChild(serviceNum);
//		System.out.println(AiCrmTree.getPathTree(root));
//		
//		AiCrmTree aicrm  = new AiCrmTree();
//		String[] paths = new String[]{"PubInfo.TransactionId", "PubInfo.InterfaceId", "PubInfo.InterfaceType", "PubInfo.OpId", "Request.BusiParams.ServiceNum[n]", "Request.BusiCode"};
//		for(int i = 0 ; i < paths.length ; i++){
//			aicrm.addChildByPath(paths[i]);
//		}
//		System.out.println(aicrm);
//		System.out.println(AiCrmTree.getPathTree(aicrm));
//
//		Map map = AiCrmTree.returnMap(aicrm);
//		System.out.println(map);
//		
//		System.out.println(AiCrmTree.assembleJSON(map));

		System.out.println(getRomId(6));
	}
}
