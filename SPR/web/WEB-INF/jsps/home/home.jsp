<%-- 
    Document   : home
    Created on : Nov 21, 2023, 7:47:09 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SR</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main_menu.css"/>
    </head>
    <body class="bg-zinc-950">
       	<div class="flex flex-col">
            <div class="flex flex-row gap-x-32">
                <%@include file="../nav_bar.jsp" %>
                <div class="flex">
                    <div class="grid min-h-screen grid-rows-2">
                        <div>
                            <section class="pt-10 text-white">
                                <p class="text-5xl">Spanish Ranking</p>
                                <p class="pt-2 max-w-sm">
                                    La mejor aplicación para el seguimiento de clanes
                                    españoles durante la temporada de clan wars.
                                </p>
                            </section>
                        </div>

                        <div>
                        </div>

                    </div>
                </div>
            </div>
            <%@include file="../footer.jsp" %>
        </div>
        <script src="https://cdn.tailwindcss.com"></script>
    </body>

</html>