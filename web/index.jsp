<%-- 
    Document   : index
    Created on : 17/04/2019, 08:15:57
    Author     : 80130917
--%>
<%@page import="java.lang.Double"%>
<%@page import="Classes.Cardapio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Servlet.XML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css">
        <title>Cardapio</title>
    </head>
    <body>

        <header class="buscarMenu">
            <h3>Clique no link abaixo e acesse nosso cardápio especial de tortas e sobremesas: </h3>
            <form action="xml" method="POST"> 
                <input type="submit" name="conhecerCardapio" value="Conhecer Cardápio">
            </form>
        </header>       

        <%
            Object cardapio = request.getAttribute("conteudoCardapio");
            if (cardapio != null) {
                ArrayList<Cardapio> menu = (ArrayList<Cardapio>) cardapio;
        %>
        <section class="secao1">
            <h3>Conheça nosso Menu de Tortas e Sobremesas</h3>   
            <table border="0">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Descricao</th>
                        <th>Calorias</th>
                        <th>Preço</th>

                    </tr>
                </thead>

                <%
                    for (int i = 0; i < menu.size(); i++) {
                        Cardapio opcoesCardapio = menu.get(i);
                %>
                <tr>
                    <td><% out.print(opcoesCardapio.getNome()); %></td>
                    <td><% out.print(opcoesCardapio.getDescricao()); %></td>
                    <td><% out.print(opcoesCardapio.getCalorias()); %></td>
                    <td>R$ <% out.print(opcoesCardapio.getPreco()); %></td>
                </tr>
                <%   }  %>
                <br />          

            </table>
        </section>

        <section class="secao2">
            <h2>Pedido</h2>
            <form action="calculacalorias" method="POST">
                <table>
                    <thead>
                        <tr >
                            <th>Nome</th>
                            <th>Quantidade</th>
                            <th>Preço por Unidade</th>
                            <th>Calorias por Unidade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < menu.size(); i++) {
                                Cardapio opcoesCardapio = menu.get(i);
                        %>
                        <tr class="item">
                            <td><% out.print(opcoesCardapio.getNome());%></td>
                            <td>
                             <a class="btn-decrementa">-</a>

                                <input max="100" class="quantidade" value="0" type="number" name="valores">  

                                <a class="btn-incrementa">+</a>
                            </td>
                           <!-- <td ><input type="number" step="any" name="quantidade_<%= i%>" min ="0" value="0"</td> -->
                            <td class="preco-item"><%out.print(opcoesCardapio.getPreco()); %></td>
                            <td class="calorias-item"><%out.print(opcoesCardapio.getCalorias()); %></td>
                        </tr>  
                        <%}%>   
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2">Total</td>
                            <td id="total">0</td>
                            <td id="totalCalorias">0</td>
                        </tr>
                    </tfoot>
                </table>
                       <!--<input type="submit" name="calcularValores" value="Calcular"> -->
            </form>
        </section>
        <script type="text/javascript" src="calcularCampos.js"></script>
        <%}%>
    </body>
</html>
