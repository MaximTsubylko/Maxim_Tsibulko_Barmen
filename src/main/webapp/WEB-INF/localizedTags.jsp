
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
    <fmt:message key="error.registration.smallpassword" var="error_17" bundle="${bundle}"/>
    <fmt:message key="error.registration.largepassword" var="error_18" bundle="${bundle}"/>
    <fmt:message key="error.create.cocktaile.description.large" var="error_19" bundle="${bundle}"/>
    <fmt:message key="error.create.cocktail.price.large" var="error_20" bundle="${bundle}"/>
    <fmt:message key="error.create.cocktail.price.sign" var="error_20" bundle="${bundle}"/>



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


    <fmt:message key="error.waiting_conf.text" var="error_128_message" bundle="${bundle}"/>


    <fmt:message key="error.blocked.text" var="error_129_message" bundle="${bundle}"/>


    <fmt:message key="error.cocktail.cannotcreatecocktwhithingr" var="error_130_message" bundle="${bundle}"/>

    <fmt:message key="error.cocktail.cantgetingredietn" var="error_131_message" bundle="${bundle}"/>

    <fmt:message key="error.customer.create" var="error_132_message" bundle="${bundle}"/>

    <fmt:message key="error.customer.delete" var="error_133_message" bundle="${bundle}"/>

    <fmt:message key="error.customer.delete" var="error_134_message" bundle="${bundle}"/>

    <fmt:message key="error.validation.incorrectlogin" var="error_135_message" bundle="${bundle}"/>

    <fmt:message key="error.cocktail.notqnique" var="error_136_message" bundle="${bundle}"/>

    <fmt:message key="error.ingredient.notunique" var="error_137_message" bundle="${bundle}"/>

    <fmt:message key="error.customer.email.notunique" var="error_138_message" bundle="${bundle}"/>

    <fmt:message key="error.customer.email.incorrect" var="error_139_message" bundle="${bundle}"/>

    <fmt:message key="error.customer.edit" var="error_140_message" bundle="${bundle}"/>

    <fmt:message key="error.customer.notuniquelogin" var="error_141_message" bundle="${bundle}"/>

    <fmt:message key="error.cocktail.largename" var="error_143_message" bundle="${bundle}"/>

    <fmt:message key="error.cocktail.descriptionsize" var="error_144_message" bundle="${bundle}"/>

    <fmt:message key="error.name.incorrect" var="error_145_message" bundle="${bundle}"/>


    <fmt:message key="error.pagenotfound" var="error_404_title" bundle="${bundle}"/>



    <span id="e_title">${title}</span>

    <span id="e12_message">${error_12}</span>
    <span id="e13_message">${error_13}</span>
    <span id="e14_message">${error_14}</span>
    <span id="e15_message">${error_15}</span>
    <span id="e16_message">${error_16}</span>
    <span id="e17_message">${error_17}</span>
    <span id="e18_message">${error_18}</span>


    <span id="e113_title">${title}</span>
    <span id="e113_message">${error_113}</span>

    <span id="e115_title">${title}</span>
    <span id="e115_message">${error_115}</span>

    <span id="e116_title">${title}</span>
    <span id="e116_message">${error_116}</span>

    <span id="e117_title">${title}</span>
    <span id="e117_message">${error_117}</span>

    <span id="e118_title">${title}</span>
    <span id="e118_message">${error_118}</span>

    <span id="e120_title">${title}</span>
    <span id="e120_message">${error_120}</span>

    <span id="e121_title">${title}</span>
    <span id="e121_message">${error_121}</span>

    <%--change state--%>
    <span id="e119_title">${title}</span>
    <span id="e119_message">${error_119}</span>

    <%--incorrect username--%>
    <span id="e122_title">${title}</span>
    <span id="e122_message">${error_122}</span>

    <%--can`t create ingredient--%>
    <span id="e123_title">${title}</span>
    <span id="e123_message">${error_123}</span>

    <span id="e124_title">${title}</span>
    <span id="e124_message">${error_124}</span>

    <%--not exist ingredient--%>
    <span id="e125_title">${title}</span>
    <span id="e125_message">${error_125}</span>

    <%--incorrect password--%>
    <span id="e126_title">${title}</span>
    <span id="e126_message">${error_126}</span>

    <%--not exist e-mail--%>
    <span id="e127_title">${title}</span>
    <span id="e127_message">${error_127}</span>

    <%--create ingredient--%>
    <span id="e134_message">${error_134_message}</span>
    <span id="e134_title">${title}</span>

    <%--delete customer--%>
    <span id="e133_message">${error_133_message}</span>
    <span id="e133_title">${title}</span>

    <span id="e135_message">${error_135_message}</span>
    <span id="e135_title">${title}</span>

    <%--create customer--%>
    <span id="e132_message">${error_132_message}</span>
    <span id="e132_title">${title}</span>


    <%--get ingredient by cocktail--%>
    <span id="e131_message">${error_131_message}</span>
    <span id="e131_title">${title}</span>

    <%--get cocktail by customer--%>
    <span id="e130_message">${error_130_message}</span>
    <span id="e130_title">${title}</span>

    <%--waiting conformation--%>
    <span id="e128_message">${error_128_message}</span>
    <span id="e128_title">${title}</span>

    <%--bloced--%>
    <span id="e129_message">${error_129_message}</span>
    <span id="e129_title">${title}</span>

    <%--not unique cocktail--%>
    <span id="e136_message">${error_136_message}</span>
    <span id="e136_title">${title}</span>

    <%--not unique ingredient--%>
    <span id="e137_message">${error_137_message}</span>
    <span id="e137_title">${title}</span>

    <%--not unique email--%>
    <span id="e138_message">${error_138_message}</span>
    <span id="e138_title">${title}</span>

    <%--incorrect email--%>
    <span id="e139_message">${error_139_message}</span>
    <span id="e139_title">${title}</span>

    <%--incorrect email--%>
    <span id="e140_message">${error_140_message}</span>
    <span id="e140_title">${title}</span>


    <%--not unique login--%>
    <span id="e141_message">${error_141_message}</span>
    <span id="e141_title">${title}</span>


    <%--large price login--%>
    <span id="e142_message">${error_20}</span>
    <span id="e142_title">${title}</span>

    <%--cocktail name size--%>
    <span id="e143_message">${error_143_message}</span>
    <span id="e143_title">${title}</span>

    <%--cocktail deacription size--%>
    <span id="e144_message">${error_144_message}</span>
    <span id="e144_title">${title}</span>

    <%--cocktail deacription size--%>
    <span id="e145_message">${error_145_message}</span>
    <span id="e145_title">${title}</span>

<%--page not found--%>
    <span id="e404_title">${error_404_title}</span>



</div>
