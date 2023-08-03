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
                        <form action="VentaTotal" method="post">
                            <input type="hidden" name="fkCliente" class="form-control" id="fkCliente">
                            <input type="hidden" name="fkMenu" class="form-control" id="fkMenu">
                            <input type="hidden" name="fkVenta" class="form-control" id="fkVenta" value="${idVenta}">
                            <input type="hidden" name="venCodigo" value="${codVenta}">
                            <input type="hidden" name="detTotal" id="detTotal">
                            <input type="hidden" name="venTotal" id="venTotal">
                            <div class="row">
                                <div class="col-xl-6 m-b30">
                                    <label class="form-label">Cliente</label>
                                    <div class="input-group mb-3">
                                        <input type="text" name="fkmenu" class="form-control" id="lblNombreCli">
                                        <button type="button" class="btn btn-primary" data-coreui-toggle="modal" data-coreui-target="#exampleModal">
                                            Buscar
                                        </button>
                                    </div>
                                </div>
                                <div class="col-sm-6 m-b30">
                                    <label class="form-label">Codigo venta</label>
                                    <input type="text" id="txtCodVenta" class="form-control" name="venCodigo" value="${codVenta}">
                                    <input type="hidden" id="txtIdVenta" class="form-control" name="txtIdVenta" value="${idVenta}">
                                </div>
                                <div class="col-sm-6 m-b30">
                                    <label class="form-label">menu</label>
                                    <div class="input-group mb-3">
                                        <input type="text" name="fkmenu" class="form-control" id="lblNombreProd">
                                        <button type="button" class="btn btn-primary" data-coreui-toggle="modal" data-coreui-target="#exampleModalMenu">
                                            Buscar
                                        </button>
                                    </div>
                                </div>

                                <div class="col-sm-6 m-b30">

                                    <label class="form-label">Cantidad</label>
                                    <div class="input-group mb-3">
                                        <input type="number" id="lbl-cantidad" class="form-control" name="detCantidad">
                                        <input type="button" class="form-control btn btn-primary" name="accion" value="Agregar detalle"
                                               onclick="agregarDetalle()">
                                    </div>
                                </div>
                                <div class="col-sm-2 m-b30 mt-2 d-flex gap-5">

                                    <input class="btn btn-primary ml-4" type="submit" name="accion" onclick="generarPDF()" value="guardar">
                                </div>
                            </div>  
                        </form>

                        <div class="table-responsive mt-5">
                            <table class="table text-center h-100 py-5" id="detalle-table">
                                <!-- ... -->
                            </table>
                        </div>
                        <div class="col-lg-12 mt-3">
                            <h4 class="text-center">Total a pagar: <span id="totalPagar">0.00</span></h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="btn-close" data-coreui-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="table-responsive">
                            <table id="example" class="display table header-border verticle-middle" style="min-width: 845px">
                                <thead>
                                    <tr>
                                        <th class="text-center">Id</th>
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
                                    <tr onclick="selectClient(this)">
                                        <td class="text-center text-muted"><%= ch.getIdCliente()%></td>
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
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="exampleModalMenu" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="btn-close" data-coreui-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="table-responsive">
                            <table id="example3" class="display table header-border table-hover verticle-middle">
                                <thead>
                                    <tr>
                                        <th class="text-center">#</th>
                                        <th class="text-center">menu</th>
                                        <th class="text-center">P.V.P</th>
                                        <th class="text-center">Stock</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- ============ INICIO DE LA CARD menu ============ -->
                                    <%
                                        MenuDao daoProd = new MenuDao();
                                        List<menu> listProd = daoProd.getAll();
                                        Iterator<menu> iterProd = listProd.iterator();
                                        menu prod = null;
                                        while (iterProd.hasNext()) {
                                            prod = iterProd.next();
                                    %>
                                    <tr onclick="selectProduct(this)">
                                        <td class="text-center text-muted"><%= prod.getIdMenu()%></td>
                                        <td class="text-center text-muted"><%= prod.getMenNombre()%></td>
                                        <td class="text-center text-muted"><%= prod.getMenPrecio()%></td>
                                        <td class="text-center text-muted"><%= prod.getMenStock()%></td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.4/js/buttons.html5.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.4/js/buttons.print.min.js"></script>
        <script>
                                        $(document).ready(function () {
                                            $('#example').DataTable({
                                                dom: 'Bfrtip',
                                                buttons: [
                                                    'copy', 'csv', 'excel', 'pdf', 'print'
                                                ]
                                            });
                                        });

                                        let selectedClient = null;
                                        let selectedProduct = null;

                                        function selectClient(row) {
                                            const cells = row.cells;
                                            selectedClient = {
                                                id: cells[0].innerText,
                                                name: cells[1].innerText + " " + cells[2].innerText,
                                            };
                                            document.getElementById('lblNombreCli').value = selectedClient.name;
                                            document.getElementById('fkCliente').value = selectedClient.id;
                                        }

                                        function selectProduct(row) {
                                            const cells = row.cells;
                                            selectedProduct = {
                                                id: cells[0].innerText,
                                                name: cells[1].innerText,
                                                price: parseFloat(cells[2].innerText),
                                                stock: parseInt(cells[3].innerText),
                                            };
                                            document.getElementById('lblNombreProd').value = selectedProduct.name;
                                            document.getElementById('fkMenu').value = selectedProduct.id;
                                        }

                                        function agregarDetalle() {
                                            const cantidadSolicitada = parseInt(document.getElementById('lbl-cantidad').value);
                                            if (!selectedClient) {
                                                alert('Debes seleccionar un cliente.');
                                                return;
                                            }
                                            if (!selectedProduct) {
                                                alert('Debes seleccionar un producto.');
                                                return;
                                            }
                                            if (isNaN(cantidadSolicitada) || cantidadSolicitada <= 0) {
                                                alert('La cantidad solicitada debe ser un número mayor a cero.');
                                                return;
                                            }
                                            if (cantidadSolicitada > selectedProduct.stock) {
                                                alert('La cantidad solicitada supera el stock disponible.');
                                                return;
                                            }

                                            const table = document.getElementById('detalle-table');
                                            const row = table.insertRow(-1);
                                            const cellId = row.insertCell(0);
                                            const cellProduct = row.insertCell(1);
                                            const cellCode = row.insertCell(2);
                                            const cellPVP = row.insertCell(3);
                                            const cellTotal = row.insertCell(4);

                                            cellId.innerText = selectedProduct.id;
                                            cellProduct.innerText = selectedProduct.name;
                                            cellCode.innerText = selectedProduct.id;
                                            cellPVP.innerText = selectedProduct.price.toFixed(2);
                                            cellTotal.innerText = (selectedProduct.price * cantidadSolicitada).toFixed(2);
                                            document.getElementById("detTotal").value = (selectedProduct.price * cantidadSolicitada).toFixed(2);

                                            // Actualizamos el total a pagar
                                            calcularTotalPagar();
                                        }

                                        function calcularTotalPagar() {
                                            let totalPagar = 0;
                                            const rows = document.querySelectorAll("#detalle-table tbody tr");
                                            rows.forEach(row => {
                                                const cellTotal = row.cells[4];
                                                const total = parseFloat(cellTotal.innerText);
                                                totalPagar += total;
                                            });

                                            // Mostramos el total a pagar en el elemento con el id "totalPagar"
                                            document.getElementById("totalPagar").innerText = totalPagar.toFixed(2);
                                            document.getElementById("venTotal").value = totalPagar.toFixed(2);
                                        }

                                        function generarPDF() {
                                            const totalPagar = document.getElementById('totalPagar').innerText;
                                            const docDefinition = {
                                                content: [
                                                    {text: 'DATOS DE LA COMPRA', style: 'header'},
                                                    {text: 'Cliente:', style: 'subheader'},
                                                    {text: selectedClient ? selectedClient.name : 'Consumidor final', style: 'content'},
                                                    {text: 'Detalles de la compra:', style: 'subheader'},
                                                    generarTablaDetalles(),
                                                    {text: '', style: 'subheader'},

                                                    {text: "Total a Pagar: " + document.getElementById('totalPagar').innerText, style: 'content'}
                                                ],
                                                styles: {
                                                    header: {
                                                        fontSize: 18,
                                                        bold: true,
                                                        alignment: 'center',
                                                        margin: [0, 0, 0, 10]
                                                    },
                                                    subheader: {
                                                        fontSize: 14,
                                                        bold: true,
                                                        margin: [0, 10, 0, 5]
                                                    },
                                                    content: {
                                                        fontSize: 12,
                                                        margin: [0, 0, 0, 10]
                                                    }
                                                }
                                            };

                                            // Generar el PDF
                                            pdfMake.createPdf(docDefinition).download('factura.pdf');
                                        }


                                        function generarTablaDetalles() {
                                            const rows = document.querySelectorAll("#detalle-table tbody tr");
                                            const detalleData = [['Producto', 'Código', 'P.V.P', 'Total']];
                                            rows.forEach(row => {
                                                const cells = row.cells;
                                                const rowData = [
                                                    cells[1].innerText,
                                                    cells[2].innerText,
                                                    cells[3].innerText, // Aquí se muestra el P.V.P sin el signo "$"
                                                    cells[4].innerText // Aquí se muestra el Total sin el signo "$"
                                                ];
                                                detalleData.push(rowData);
                                            });

                                            return {
                                                table: {
                                                    headerRows: 1,
                                                    widths: ['*', '*', '*', '*'],
                                                    body: detalleData
                                                }
                                            };
                                        }
        </script>
    </body>
</html>S