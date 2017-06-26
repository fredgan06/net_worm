package org.mybatis.generator.plugins;


import java.util.List;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;




/**
 * This plugin adds limit and offset clause to the example class and
 * to the sqlMap selectByExample map.
 *
 * @author Marco Musu
 *
 */
public class AddLimitOffsetPlugin extends PluginAdapter {

    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();

        Field limit = new Field();
        limit.setName("limit");
        limit.setVisibility(JavaVisibility.PRIVATE);
        limit.setType(integerWrapper);
        topLevelClass.addField(limit);

        Method limitSet = new Method();
        limitSet.setVisibility(JavaVisibility.PUBLIC);
        limitSet.setName("setLimit");
        limitSet.addParameter(new Parameter(integerWrapper, "limit"));
        limitSet.addBodyLine("this.limit = limit;");
        topLevelClass.addMethod(limitSet);

        Method limitGet = new Method();
        limitGet.setVisibility(JavaVisibility.PUBLIC);
        limitGet.setReturnType(integerWrapper);
        limitGet.setName("getLimit");
        limitGet.addBodyLine("return limit;");
        topLevelClass.addMethod(limitGet);

        Field offset = new Field();
        offset.setName("offset");
        offset.setVisibility(JavaVisibility.PRIVATE);
        offset.setType(integerWrapper);
        topLevelClass.addField(offset);

        Method offsetSet = new Method();
        offsetSet.setVisibility(JavaVisibility.PUBLIC);
        offsetSet.setName("setOffset");
        offsetSet.addParameter(new Parameter(integerWrapper, "offset"));
        offsetSet.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(offsetSet);

        Method offsetGet = new Method();
        offsetGet.setVisibility(JavaVisibility.PUBLIC);
        offsetGet.setReturnType(integerWrapper);
        offsetGet.setName("getOffset");
        offsetGet.addBodyLine("return offset;");
        topLevelClass.addMethod(offsetGet);

        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        @SuppressWarnings("unused")
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();

        //XmlElement lastElement = (XmlElement)element.getElements().get(element.getElements().size());

        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "limit != null"));
        isNotNullElement.addElement(new TextElement("limit ${limit}"));
        element.getElements().add(isNotNullElement);


        isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "offset != null"));
        isNotNullElement.addElement(new TextElement("offset ${offset}"));
        element.getElements().add(isNotNullElement);
        return true;
    }
}