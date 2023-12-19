<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CRUD</title>
        <meta charset="UTF-8">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" type="text/css" href="/SPR/css/main_menu.css"/>
    </head>

    <body class="bg-zinc-950">
       	<div class="flex flex-col">
            <div class="">
                <%@include file="../nav_bar.jsp" %>
                <div class="pl-14 flex flex-row justify-around gap-x-24">
                    <div class="flex justify-around text-start" style="justify-content:space-around">
                        <section class="mt-10 text-red-50 text-center">
                            <p class="max-h-fit text-4xl text-red-50">Listado de tanques</p>
                            <div class="pt-20"></div>
                            <div id="table_div" class="flex justify-around">
                                <%@include file="tank_table.jsp" %>
                            </div>
                            <div class="pt-10">
                                <button id="previous_page" class="justify-self-center border rounded-full mx-14 px-4 text-lime-400 ">Anterior</button>
                                <span id="page_text"> 
                                </span>
                                <button id="next_page" class="justify-self-center border rounded-full mx-14 px-4 text-lime-400 ">Siguiente</button>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
            
            <%@include file="../footer.jsp" %>
        </div>
        <script src="/SPR/js/tanks_view.js"></script>
    </body>
</html>


