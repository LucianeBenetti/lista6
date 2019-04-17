package Servlet;

import Classes.Cardapio;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XML extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String conhecerCardapio = request.getParameter("conhecerCardapio");
   
        if (conhecerCardapio != null) {
            try {

                File arquivoEstados = new File("C:\\SENAC\\XML\\cardapio.xml");
                FileInputStream encontrarArquivo = new FileInputStream(arquivoEstados);

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

                Document doc = dBuilder.parse(encontrarArquivo);

                doc.getDocumentElement().normalize();

                NodeList nList = doc.getElementsByTagName("refeicao");

                System.out.println("----------------------------");

                ArrayList<Cardapio> conteudoCardapio = new ArrayList<>();

                for (int i = 0; i < nList.getLength(); i++) {
                    Cardapio menu = new Cardapio();
                    String cardapio = nList.item(i).getNodeName();

                    NodeList childs = nList.item(i).getChildNodes();
                    menu.setNome(childs.item(1).getTextContent());
                    menu.setPreco(Double.parseDouble(childs.item(3).getTextContent()));
                    menu.setDescricao(childs.item(5).getTextContent());
                    menu.setCalorias(Integer.parseInt(childs.item(7).getTextContent()));
                    conteudoCardapio.add(menu);

                }
                request.setAttribute("conteudoCardapio", conteudoCardapio);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
