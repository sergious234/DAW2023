<%-- 
    Document   : user_conf
    Created on : Nov 27, 2023, 8:18:44 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/SPR/css/main_menu.css"/>
    </head>
    <body class="bg-zinc-950">
       	<div class="flex flex-col">
            <div class="flex flex-row gap-x-32">
                <%@include file="../nav_bar.jsp" %> 
                <div class="flex flex-col">
                    <p class="text-5xl pt-10 text-white"> Configuración de la cuenta </p>
                    <div class="pt-20 max-w-fit">
                        <a class="inline flex space-x-3 bg-red-500 rounded-lg text-lg px-3">
                            <button class="">Cerrar sesión</button>
                        </a>
                    </div>
                </div>

            </div>
        </div>

        <%@include file="../footer.jsp" %>
        <script src="https://cdn.tailwindcss.com"></script>
    </body>
</html>
