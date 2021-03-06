<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Force database installation</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database initialization in progress</h2>
        <%
            /* How to customize :
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "demodb";
            Class.forName("com.mysql.jdbc.Driver");
            /* this will generate database if not exist */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management", "root", "");
            Statement stmt = con.createStatement();
            
            
            
            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
            String data[] = new String[]{
                "INSERT INTO `team_management`.`usuari` (`NIF`, `nom`, `cognom1`, `cognom2`, `adress`, `telefon`, `id_usuari`, `dataNaix`, `contrasenya`, `dataIncorp`) VALUES ('69940687K', 'n', 'n', NULL, NULL, '977750232', 'provaJ', '2019-12-12', 'md', '0000-00-00');",
            };
            for (String datum : data) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
