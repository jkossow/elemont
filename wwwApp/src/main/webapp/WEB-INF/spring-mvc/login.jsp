<%-- 
    Document   : hello
    Created on : 2016-02-27, 13:15:58
    Author     : jkossow
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib 
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Logowanie jsp</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <p/>
        <p style="text-align: center">
            <img src="<%=request.getContextPath()%>/images/logo.png"  style="text-align: center " /> 
        </p>
        <p/>
        <fieldset   style="width:25%; margin: 0 auto">
            
        <form id="login_form"  method="post">
            
            <table>
                <tr> 
                    <td>
                        <label for="username" class="login-form-tag">Użytkownik:</label> 
                    </td>
                    <td>
                        <input type="text" id="username" name="username" class="ui-corner-all"
                            required="required" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password" class="login-form-tag">Hasło:</label>
                    </td>
                    <td>
                        <input type="password" id="password" name="password"
                            class="ui-corner-all" required="required" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit"
                            class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
                            <span class="ui-button-text">Login</span>
                        </button>
                    </td>
                </tr>    
            </table>
        </form>
        </fieldset>
            
        <p style="text-align: center; color:red">
            <c:if test="${param.error != null }" >
                <c:out value="Brak użytkownika lub błędne hasło"/>
            </c:if>
            
            <c:if test="${param.logout != null }" >
                <c:out value="Zostałeś wylogowany"/>
            </c:if>
        </p>
        
    </body>
</html>