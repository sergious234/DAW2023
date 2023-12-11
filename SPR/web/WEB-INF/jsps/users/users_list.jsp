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
            <div class="flex flex-row gap-x-32">
                <%@include file="../nav_bar.jsp" %>
                <div class="text-center">
                    <div class="pt-20"></div>
                    <%@include file="users_table.jsp" %>
                </div>
            </div>
        </div>
    </body>
</html>
