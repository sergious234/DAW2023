<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@page import="models.Usuario" %>
        <%@page import="models.PermLvls" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Configuracion</title>
    <link rel="stylesheet" type="text/css" href="/SPR/css/main_menu.css" />
    <link rel="stylesheet" type="text/css" href="/SPR/css/dropper.css" />
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-zinc-950">
    <div class="flex flex-col">
        <div class="flex flex-row gap-x-32">
            <%@include file="../nav_bar.jsp" %>
                <div class="flex flex-col">
                    <p class="text-5xl pt-10 text-white"> Configuración de la cuenta </p>

                    <% Usuario user_obj=(Usuario) request.getAttribute("user_obj"); 
                    
                        out.println("<a class=\"space-x-3 text-white pt-4 text-lg\">"
                        + "Bienvenido "
                        + user_obj.getUserName() + " !"
                        + "</a>");

                        out.println("<a class=\"space-x-3 text-white pt-4 text-lg\">"
                            + "Tu nivel de permisos es: "
                            + user_obj.get_perm_lvl()
                            + "</a>");
                    %>
                    <div class="pt-20 max-w-fit">
                        <a class="inline flex space-x-3 bg-red-500 rounded-lg text-lg px-3">
                            <button id="close_session_button" class="">Cerrar sesión</button>
                        </a>
                    </div>

                    <div class="text-5xl pt-10 text-white">
                        <a> Subir replay: </a>
                        <div class="flex flex-wrap content-center justify-center w-128 h-72 border-dashed border-2 border-sky-500 mt-10 text-sm italic" ondrop="dropHandler(event);" ondragover="dragOverHandler(event);">
                            Arrastra aquí el archivo 
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../footer.jsp" %>
        <script src="/SPR/js/user_conf_scripts.js"></script>
        <script src="/SPR/js/dropper.js"></script>
    </body>
</html>