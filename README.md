# GYM JK

## Descripción del proyecto

GymJK es una aplicación creada para llevar los registros de un gimnasio y de los usuarios que interactúan en un gym, en este caso designamos un administrador que se encarga de llevar el control de todas las acciones que se realizan en este establecimiento, también tenemos entrenadores y los clientes, estos pueden estar registrados o no, el proyecto se realizó con lenguaje java en el IDE de NetBeans y utilizamos MySQL para hacer la base de datos. 

## Propósito del proyecto

El próposito de este proyecto es proporcionar una herramienta eficaz para la gestión de un gimnasio adaptandose a las necesidades de los diferentes tipos de usuarios, como el administrador o encargado, los entrenadores y los diversos clientes mediante la asignación de permisos y privilegios específicos, desde el ingreso de los usuarios, hasta el registro en la base de datos, dependiendo del rol de cada usuario.

## Características Clave

- Este sistema puede ser utilizado para llevar la organización de cualquier gimnasio ya que es adaptable.
- Ofrece a los administradores herramientas para gestionar usuarios, clientes, entrenadores, inscripciones y membresías.
- Permite a los clientes hacer cambios en su registro de inscripcion al tener la ocpion de cambiar de entrenador o de tipo de membresia, del mismo modo, le es posible visualizar sus rutinas y dietas asignadas.
- Permite realizar el envío de correos electrónicos con archivos adjuntos.

## Arquitectura del Proyecto

El proyecto sigue el patrón de diseño Modelo-Vista-Controlador (MVC), que consiste en separar las clases en los paquetes, modelo-vista-controlador, además de estos utilizamos los paquetes de Utilidades, donde se realizo la clase que contiene las validaciones utilizadas en el sistema y el de Seguridad donde se realizo la clase de Encriptación en la cual se utilizo el hash SHA-256 para encriptar la contraseña con 64 dígitos.

### Modelo

Las clases del modelo representan las entidades del sistema y gestionan la lógica del proyecto y la interacción con la base de datos. Contiene las clases normales y las DAO, que sirven para acceder a los datos de cada tabla:

- Usuario.java
- Cliente.java
- Entrenador.java
- Rutina.java
- Dieta.java
- Inscripciones.java
- Membresias.java


- UsuarioDAO.java
- ClienteDAO.java
- EntrenadorDAO.java
- RutinaDAO.java
- DietaDAO.java
- InscripcionesDAO.java
- MembresiasDAO.java

### Vista

Contiene las clases que gestionan la interfaz gráfica del usuario, es decir, donde se realizó el diseño de las interfaces utilizadas para mostrar el funcionamiento del sistema:

- EnvioCorreos.java
- GestionInscripcionCliente.java
- GestionarClientes.java
- GestionarDietaCliente.java
- GestionarDietas.java
- GestionarDietasCliente.java
- GestionarEntrenador.java
- GestionarInscripciones.java
- GestionarMembresia.java
- GestionarRutinaCliente.java
- GestionarRutinas.java
- GestionarRutinasCliente.java
- GestionarUsuarios.java
- Login.java
- NewUser.java
- RegistroCliente.java
- RegistroDieta.java
- RegistroEntrenador.java
- RegistroInscripcion.java
- RegistroMembresia.java
- RegistroRutina.java
- VistaCliente.java
- VistaEntrenador.java
- VistaMembresia.java
- VistaMenu.java
- VisualizarClientes.java

### Controlador
Los controladores gestionan la lógica de la aplicación y actúan como intermediarios entre el modelo y la vista. Manejan los eventos de la interfaz de usuario y actualizan el modelo:


- Conexion.java
- ControladorCliente.java
- ControladorEntrenador.java
- ControladorGestionarClientes.java
- ControladorGestionarDietas.java
- ControladorGestionarEntrenador.java
- ControladorGestionarInscripciones.java
- ControladorGestionarMembresias.java
- ControladorGestionarRutinas.java
- ControladorGestionarUsuarios.java
- ControladorInscripcion.java
- ControladorLogin.java
- ControladorMembresia.java
- ControladorNewUser.java
- ControladorRegistroDieta.java
- ControladorRegistroRutina.java
- ControladorRutinasCliente.java
- ControladorVistaDietas.java
- ControladorVistaInscripcion.java
- ControladorVistaMembresia.java

