package com.hjq.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/home")
public class HomeController extends BaseController {

	@RequestMapping("/test")
	public String test(Model model, HttpServletResponse response) {
		
		return redirectUrl("http://119.255.59.122:8102");
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	public static void main(String[] args) throws Exception {
		
		String content = "{\"xwlMeta\":\"module\",\"id\":\"module\",\"title\":\"234\",\"children\":[{\"xwlMeta\":\"viewport\",\"id\":\"viewport1\",\"layout\":\"absolute\",\"children\":[{\"xwlMeta\":\"menu\",\"id\":\"menu1\",\"ignoreParentClicks\":\"true\",\"hidden\":\"true\",\"floating\":\"true\",\"ignoreEvents\":\"true\"},{\"xwlMeta\":\"date\",\"id\":\"date1\",\"height\":22,\"width\":200,\"y\":80,\"x\":160},{\"xwlMeta\":\"toolbar\",\"enableOverflow\":\"false\",\"id\":\"toolbar1\",\"children\":[{\"xwlMeta\":\"label\",\"text\":\"label1\",\"id\":\"label1\",\"disabled\":\"123\"},{\"xwlMeta\":\"date\",\"id\":\"date2\",\"bgColor\":\"#3366FF\",\"bgImage\":\"#23VA7K8ZMV4M (3天)\",\"disabledDates\":\"123\",\"blankText\":\"123\",\"disabledDays\":\"3\",\"disabledDatesText\":\"23\"}]}]},{\"xwlMeta\":\"ajax\",\"id\":\"ajax1\"}]}";
		ObjectMapper mapper = new ObjectMapper();
//		Parent parent = mapper.readValue(content, Parent.class);
//		HashMap map = mapper.readValue(content, HashMap.class);
		JsonNode rootNode = mapper.readValue(content, JsonNode.class);
//		JsonParser jp = new ObjectMapper().getJsonFactory().createJsonParser(content);
//		JsonNode node = mapper.readTree(jp);
//		System.out.println(node);
		System.out.println(rootNode.get("children"));
	}

	public static class TestMap {
		private Map<String, Object> map;

		public Map<String, Object> getMap() {
			return map;
		}

		public void setMap(Map<String, Object> map) {
			this.map = map;
		}
		
	}

	public static class Parent {

		private String xwlMeta;
		private String id;
		private String title;
		private List<Object> children;

		public String getXwlMeta() {
			return xwlMeta;
		}
		public void setXwlMeta(String xwlMeta) {
			this.xwlMeta = xwlMeta;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public List<Object> getChildren() {
			return children;
		}
		public void setChildren(List<Object> children) {
			this.children = children;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	public static class Children {

		private Element1 element1;
		private Element2 element2;
		private Element6 element6;
		public Element1 getElement1() {
			return element1;
		}
		public void setElement1(Element1 element1) {
			this.element1 = element1;
		}
		public Element2 getElement2() {
			return element2;
		}
		public void setElement2(Element2 element2) {
			this.element2 = element2;
		}

		public Element6 getElement6() {
			return element6;
		}
		public void setElement6(Element6 element6) {
			this.element6 = element6;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	public static class Element1 {

		private String xwlMeta;
		private String id;
		private String ignoreParentClicks;
		private String hidden;
		private String floating;
		private String ignoreEvents;
		public String getXwlMeta() {
			return xwlMeta;
		}
		public void setXwlMeta(String xwlMeta) {
			this.xwlMeta = xwlMeta;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getIgnoreParentClicks() {
			return ignoreParentClicks;
		}
		public void setIgnoreParentClicks(String ignoreParentClicks) {
			this.ignoreParentClicks = ignoreParentClicks;
		}
		public String getHidden() {
			return hidden;
		}
		public void setHidden(String hidden) {
			this.hidden = hidden;
		}
		public String getFloating() {
			return floating;
		}
		public void setFloating(String floating) {
			this.floating = floating;
		}
		public String getIgnoreEvents() {
			return ignoreEvents;
		}
		public void setIgnoreEvents(String ignoreEvents) {
			this.ignoreEvents = ignoreEvents;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	public static class Element2 {

		private String xwlMeta;
		private String id;
		private String height;
		private String width;
		private String y;
		private String x;
		public String getXwlMeta() {
			return xwlMeta;
		}
		public void setXwlMeta(String xwlMeta) {
			this.xwlMeta = xwlMeta;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getHeight() {
			return height;
		}
		public void setHeight(String height) {
			this.height = height;
		}
		public String getWidth() {
			return width;
		}
		public void setWidth(String width) {
			this.width = width;
		}
		public String getY() {
			return y;
		}
		public void setY(String y) {
			this.y = y;
		}
		public String getX() {
			return x;
		}
		public void setX(String x) {
			this.x = x;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	public static class Element3 {
		
		private String xwlMeta;
		private String enableOverflow;
		private String id;
		private List<Object> children;
		public String getXwlMeta() {
			return xwlMeta;
		}
		public void setXwlMeta(String xwlMeta) {
			this.xwlMeta = xwlMeta;
		}
		public String getEnableOverflow() {
			return enableOverflow;
		}
		public void setEnableOverflow(String enableOverflow) {
			this.enableOverflow = enableOverflow;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public List<Object> getChildren() {
			return children;
		}
		public void setChildren(List<Object> children) {
			this.children = children;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	public static class Element4 {
		private String xwlMeta;
		private String text;
		private String id;
		private String disabled;
		public String getXwlMeta() {
			return xwlMeta;
		}
		public void setXwlMeta(String xwlMeta) {
			this.xwlMeta = xwlMeta;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDisabled() {
			return disabled;
		}
		public void setDisabled(String disabled) {
			this.disabled = disabled;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	public static class Element5 {

		private String xwlMeta;
		private String id;
		private String bgColor;
		private String bgImage;
		private String disabledDates;
		private String blankText;
		private String disabledDays;
		private String disabledDatesText;
		public String getXwlMeta() {
			return xwlMeta;
		}
		public void setXwlMeta(String xwlMeta) {
			this.xwlMeta = xwlMeta;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getBgColor() {
			return bgColor;
		}
		public void setBgColor(String bgColor) {
			this.bgColor = bgColor;
		}
		public String getBgImage() {
			return bgImage;
		}
		public void setBgImage(String bgImage) {
			this.bgImage = bgImage;
		}
		public String getDisabledDates() {
			return disabledDates;
		}
		public void setDisabledDates(String disabledDates) {
			this.disabledDates = disabledDates;
		}
		public String getBlankText() {
			return blankText;
		}
		public void setBlankText(String blankText) {
			this.blankText = blankText;
		}
		public String getDisabledDays() {
			return disabledDays;
		}
		public void setDisabledDays(String disabledDays) {
			this.disabledDays = disabledDays;
		}
		public String getDisabledDatesText() {
			return disabledDatesText;
		}
		public void setDisabledDatesText(String disabledDatesText) {
			this.disabledDatesText = disabledDatesText;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	public static class Element6 {

		private String xwlMeta;
		private String id;
		public String getXwlMeta() {
			return xwlMeta;
		}
		public void setXwlMeta(String xwlMeta) {
			this.xwlMeta = xwlMeta;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}
}
