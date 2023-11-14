<%-- 
    Document   : tanks_crud
    Created on : Nov 9, 2023, 12:03:32 AM
    Author     : sergio
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "models.Tanque" %>
<%@page language="java" import="java.util.List,java.lang.*" %> 
<!DOCTYPE html>
<html>
    <head>
        <title>CRUD</title>
        <meta charset="UTF-8">
        <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
        <link rel="stylesheet" type="text/css" href="/SPR/css/main_menu.css"/>
    </head>

    <body class="bg-zinc-950">
       	<div class="flex flex-col">
            <div class="flex flex-row gap-x-32">
                <nav class="main_menu basis-15 flex-initial space-y-4 pl-3 min-h-screen w-12">
                    <div class="mt-3 mr-2">
                        <a class="block space-x-3" href="https://github.com/sergious234">
                            <img class="no-invert rounded-full" src="/SPR/menu_icons/Sergious.jpg" alt="alt"/>
                            <span class="w-full justify-center text-center">
                                Sergious
                            </span>
                        </a>
                    </div>
                    <div class="pt-20">
                        <a class="inline flex space-x-3" href="ranking.html">
                            <img src="/SPR/menu_icons/ranking.svg" alt="alt"/>
                            <span class="inline">Ranking</span>
                        </a>
                    </div>
                    <div>
                        <a class="inline flex space-x-3" href="jugadores.html">
                            <img src="/SPR/menu_icons/player.svg" alt="alt"/>
                            <span>Jugadores</span>
                        </a>
                    </div>
                    <div>
                        <a class="inline flex space-x-3" href="tanques.html">
                            <img src="/SPR/menu_icons/tank.svg" alt="alt"/>
                            <span>Tanques</span>
                        </a>
                    </div>
                    <div>
                        <a class="inline flex space-x-3" href="clanes.html">
                            <img src="/SPR/menu_icons/clan.svg" alt="alt"/>
                            <span>Clanes</span>
                        </a>
                    </div>
                    <div>
                        <a class="inline flex space-x-3" href="clanes.html">
                            <img src="/SPR/menu_icons/formulario.svg" alt="alt"/>
                            <span>Formulario</span>
                        </a>
                    </div>
                </nav>
                <!--
                    En caso de que se empiece a ver
                    chungo cambiar el div por este otro:
                    <div class="grid grid-cols-9">
                -->
                <!-- <div class="flex flex-rows justify-between"> -->
                    <div class="mt-10 text-start col-span-2">
                        <p class="text-4xl text-red-50">Nuevo tanque</p>
                        <div class="pt-20"></div>
                        <div>
                            <form id="add_form" class="grid grid-cols-6 text-red-50">
                                <div class="col-span-2 grid grid-rows-4 gap-y-5 max-w-min justify-items-start">
                                    <label>Nombre </label>
                                    <label>Daño </label>
                                    <label>HP </label>
                                    <label>  </label>
                                </div>

                                <div class="grid col-span-4 grid-rows-4 gap-y-5 justify-items-start">
                                    <input required id="tank_name" class="text-black pl-2" type="text" placeholder="Nombre"/>
                                    <input required id="tank_dmg" class="text-black pl-2" type="text" placeholder="Daño"/>
                                    <input required  id="tank_hp" class="text-black pl-2" type="text" placeholder="HP"/>
                                    <!-- <label>Aceptar </label> -->
                                    <input id="add_submit" class="justify-self-center border rounded-full px-4 text-lime-400" type="submit" value="Insertar"/>
                                </div>


                            </form>
                        </div>
                    </div>
                    <div class="flex col-span-4 justify-center text-start">
                        <section class="mt-10 text-red-50 text-center">
                            <p class="max-h-fit text-4xl text-red-50">Listado de tanques</p>
                            <div class="pt-20"></div>
                            <table class="table-fixed">
                                <thead>
                                    <tr>
                                        <th class="min-w-[1.5rem]">    </th>
                                        <th class="min-w-[2.0rem]">    </th>
                                        <th class="border w-14">ID</th>
                                        <th class="border w-52">NOMBRE</th>
                                        <th class="border w-24">DAÑO</th>
                                        <th class="border w-24">VIDA</th>
                                    </tr>
                                </thead>
                                <tbody id="table_body"> 
                                    <%@include file="tank_table.jsp" %>
                                </tbody>
                            </table>
                        </section>

                    </div>
                    <div class="pl-24 col-span-2 mt-10 text-center">
                        <p class="text-4xl text-red-50">Modificar Tanque</p>
                        <div class="pt-20"></div>
                        <form id="update_form" class="grid grid-cols-6 text-red-50">
                            <div class="col-span-2 grid grid-rows-5 gap-y-5 max-w-min justify-items-start">
                                <label>ID</label>
                                <label>Nombre</label>
                                <label>Daño</label>
                                <label>HP</label>
                                <label>  </label>
                            </div>
                            <div class="grid col-span-4 grid-rows-5 gap-y-5 justify-items-start">
                                <input required id="update_tank_id" class="text-black min-w-full pl-2" pattern="[0-9]+" type="text" placeholder="ID">
                                <input required id="new_tank_name" class="text-black min-w-full pl-2"  type="text" placeholder="Nombre">
                                <input required id="new_tank_dmg" class="text-black min-w-full pl-2" type="text" placeholder="Daño">
                                <input required id="new_tank_hp" class="text-black min-w-full pl-2" type="text" placeholder="HP">
                                <input id="update_submit" class="justify-self-center border rounded-full px-4 text-amber-400" type="submit" value="Update"/>
                            </div>

                        </form>
                    </div>
                </div>
            <!-- </div> -->
            <footer class="pie_menu z-1 static inset-x-0 bottom-0 h-20 items-center grid ">
                <section class="text-center">
                    Página creada por Sergious234 (saurio).<br>
                    No copyright (considera regalarme un poco de oro).
                </section>
            </footer>
        </div>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="/SPR/js/tanks_crud.js"></script>
    </body>
</html>


