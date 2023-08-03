<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.restaurant.model.*"%>
<%@page import="com.restaurant.dao.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <base href="./">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta name="description" content="CoreUI - Open Source Bootstrap Admin Template">
        <meta name="author" content="Łukasz Holeczek">
        <meta name="keyword" content="Bootstrap,Admin,Template,Open,Source,jQuery,CSS,HTML,RWD,Dashboard">
        <title>CoreUI Free Bootstrap Admin Template</title>
        <link rel="apple-touch-icon" sizes="57x57" href="assets/favicon/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="assets/favicon/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="assets/favicon/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="assets/favicon/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="assets/favicon/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="assets/favicon/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="assets/favicon/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="assets/favicon/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192" href="assets/favicon/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="assets/favicon/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
        <link rel="manifest" href="assets/favicon/manifest.json">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="msapplication-TileImage" content="assets/favicon/ms-icon-144x144.png">
        <meta name="theme-color" content="#ffffff">
        <!-- Vendors styles-->
        <link rel="stylesheet" href="vendors/simplebar/css/simplebar.css">
        <link rel="stylesheet" href="css/vendors/simplebar.css">
        <!-- Main styles for this application-->
        <link href="css/style.css" rel="stylesheet">
        <!-- We use those styles to show code examples, you should remove them in your application.-->
        <link href="css/examples.css" rel="stylesheet">
        <link href="vendors/@coreui/chartjs/css/coreui-chartjs.css" rel="stylesheet">
        <!-- Tablas -->
        <link href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/buttons/2.3.4/css/buttons.dataTables.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
    </head>
    <body>
        <div class="sidebar sidebar-dark sidebar-fixed" id="sidebar">
            <div class="sidebar-brand d-none d-md-flex">
                RESTAURANT
            </div>
            <ul class="sidebar-nav" data-coreui="navigation" data-simplebar="">
                <li class="nav-item">
                    <a class="nav-link" href="Controlador?p=ventas">
                        Ventas
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Controlador?p=clientes">
                        Clientes
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Controlador?p=productos">
                        menu
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Controlador?p=usuarios">
                        Usuarios
                    </a>
                </li>

            </ul>
            <button class="sidebar-toggler" type="button" data-coreui-toggle="unfoldable"></button>
        </div>
        <div class="wrapper d-flex flex-column min-vh-100 bg-light">
            <header class="header header-sticky mb-4">
                <div class="container-fluid">
                    <button class="header-toggler px-md-0 me-md-3" type="button" onclick="coreui.Sidebar.getInstance(document.querySelector('#sidebar')).toggle()">
                        <svg class="icon icon-lg">
                        <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-menu"></use>
                        </svg>
                    </button><a class="header-brand d-md-none" href="#">
                        <svg width="118" height="46" alt="CoreUI Logo">
                        <use xlink:href="assets/brand/coreui.svg#full"></use>
                        </svg></a>


                    <ul class="header-nav ms-3">
                        <li class="nav-item dropdown"><a class="nav-link py-0" data-coreui-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <div class="text-uppercase">${usuUsuario}</div>
                            </a>
                            <div class="dropdown-menu dropdown-menu-end pt-0">
                                <a class="dropdown-item" href="CerrarSesion">
                                    <svg class="icon me-2">
                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-account-logout"></use>
                                    </svg> Cerrar Sesion</a>
                            </div>
                        </li>
                    </ul>
                </div>

            </header>
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="card p-3">
                        <div class="col-lg-12">
                            <a href="Clientes?accion=nuevo" class="btn btn-primary rounded-pill mb-3">Nuevo registro</a>
                            <div class="card">
                                <div class="card-body p-4">
                                    <div class="table-responsive">
                                        <table id="example" class="display table header-border verticle-middle" style="min-width: 845px">
                                            <thead>
                                                <tr>
                                                    <th/>                                                 
                                                    <th class="text-center">Nombres</th>
                                                    <th class="text-center">Apellidos</th>
                                                    <th class="text-center">Cedula</th>   
                                                    <th class="text-center">Email</th>                                                    
                                                    <th class="text-center">Telefono</th>                                                  
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    ClienteDao daoCh = new ClienteDao();
                                                    List<cliente> listCh = daoCh.getAll();
                                                    Iterator<cliente> iterCh = listCh.iterator();
                                                    cliente ch = null;
                                                    while (iterCh.hasNext()) {
                                                        ch = iterCh.next();
                                                %>
                                                <tr>                                                    
                                                    <td class="d-flex justify-items-center gap-3"> 

                                                        <div>
                                                            <a href="Clientes?accion=editar&idCliente=<%= ch.getIdCliente()%>" class="btn-primary btn"><i class="fas fa-pencil-alt text-primary"></i>&nbsp; Actualizar</a>
                                                        </div>
                                                        <div>
                                                            <a href="Clientes?accion=eliminar&idCliente=<%= ch.getIdCliente()%>" class="btn-danger btn"><i class="fas fa-arrow-circle-down text-secondary"></i>&nbsp; Eliminar</a>
                                                        </div>
                                                    </td>	                                               
                                                    <td class="text-center text-muted"><%= ch.getCliNombre()%></td>                                          
                                                    <td class="text-center text-muted"><%= ch.getCliApellido()%></td>                                          
                                                    <td class="text-center text-muted"><%= ch.getCliCedula()%></td>                                          
                                                    <td class="text-center text-muted"><%= ch.getCliEmail()%></td>                                          
                                                    <td class="text-center text-muted"><%= ch.getCliTelefono()%></td>                                          
                                                </tr>   
                                                <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div
                    </div>
                </div>
            </div>
        </div>


        <!-- CoreUI and necessary plugins-->
        <script src="vendors/@coreui/coreui/js/coreui.bundle.min.js"></script>
        <script src="vendors/simplebar/js/simplebar.min.js"></script>
        <!-- Plugins and scripts required by this view-->
        <script src="vendors/chart.js/js/chart.min.js"></script>
        <script src="vendors/@coreui/chartjs/js/coreui-chartjs.js"></script>
        <script src="vendors/@coreui/utils/js/coreui-utils.js"></script>
        <script src="js/main.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <!-- PDFS -->
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.4/js/dataTables.buttons.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js" ></script>
        <script src="https://cdn.datatables.net/buttons/2.3.4/js/buttons.html5.min.js" ></script>
        <script src="https://cdn.datatables.net/buttons/2.3.4/js/buttons.print.min.js" ></script>
        <script>
                        $(document).ready(function () {
                            $('#example').DataTable({
                                dom: 'Bfrtip',
                                buttons: [
                                    'copy', 'csv', 'excel', 'pdf', 'print'
                                ]
                            });
                        });
        </script>

    </body>
</html>