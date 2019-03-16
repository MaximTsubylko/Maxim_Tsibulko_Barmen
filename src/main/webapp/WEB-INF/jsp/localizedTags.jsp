
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>



<div style="display: none">
    <fmt:message key="error.title" var="title" bundle="${bundle}"/>
    <fmt:message key="error.registration.notvalidemail" var="error_12" bundle="${bundle}"/>
    <fmt:message key="error.registration.notmatchpassword" var="error_13" bundle="${bundle}"/>
    <fmt:message key="error.registration.lesslonger" var="error_14" bundle="${bundle}"/>
    <fmt:message key="error.registration.morelonger" var="error_15" bundle="${bundle}"/>
    <fmt:message key="error.registration.notvalidlogin" var="error_16" bundle="${bundle}"/>

    <fmt:message key="error.dao" var="error_113" bundle="${bundle}"/>
    <fmt:message key="error.cocktailfeedback.get" var="error_115" bundle="${bundle}"/>
    <fmt:message key="error.cocktailfeedback.create" var="error_116" bundle="${bundle}"/>
    <fmt:message key="error.customerfeedback.get" var="error_117" bundle="${bundle}"/>
    <fmt:message key="error.customerfeedback.create" var="error_118" bundle="${bundle}"/>
    <fmt:message key="error.state.change" var="error_119" bundle="${bundle}"/>
    <fmt:message key="error.password.change" var="error_120" bundle="${bundle}"/>
    <fmt:message key="error.customer.notunique" var="error_121" bundle="${bundle}"/>
    <fmt:message key="error.customer.notexist" var="error_122" bundle="${bundle}"/>
    <fmt:message key="error.cocktail.cannotcreatecocktwhithingr" var="error_123" bundle="${bundle}"/>
    <fmt:message key="error.cocktail.notexist" var="error_124" bundle="${bundle}"/>
    <fmt:message key="error.ingredient.notexist" var="error_125" bundle="${bundle}"/>
    <fmt:message key="error.validation.incorrectpassword" var="error_126" bundle="${bundle}"/>
    <fmt:message key="error.validation.notexistemil" var="error_127" bundle="${bundle}"/>




    <span id="e_title">${title}</span>

    <span id="e12_message">${error_12}</span>
    <span id="e13_message">${error_13}</span>
    <span id="e14_message">${error_14}</span>
    <span id="e15_message">${error_15}</span>
    <span id="e16_message">${error_16}</span>

    <span id="e113_message">${error_113}</span>
    <span id="e115_message">${error_115}</span>
    <span id="e116_message">${error_116}</span>
    <span id="e117_message">${error_117}</span>
    <span id="e118_message">${error_118}</span>
    <span id="e119_message">${error_119}</span>
    <span id="e120_message">${error_120}</span>
    <span id="e121_message">${error_121}</span>
    <span id="e122_message">${error_122}</span>
    <span id="e123_message">${error_123}</span>
    <span id="e124_message">${error_124}</span>
    <span id="e125_message">${error_125}</span>
    <span id="e126_message">${error_126}</span>
    <span id="e127_message">${error_127}</span>


</div>
