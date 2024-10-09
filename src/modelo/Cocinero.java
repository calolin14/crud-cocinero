package modelo;
     
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmCocinero;


public class Cocinero {

    //1- Parametros
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    private String uuid_cocinero;
    private String nombre;
    private int edad;
    private int peso;

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    private String correo;
    
    public String getuuid_cocinero() {
        return uuid_cocinero;
    }

    public void setuuid_cocinero(String uuid_cocinero) {
        this.uuid_cocinero = uuid_cocinero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }
    
    
        ////////////////////////3- Métodos 
   public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement nuevoCocinero = conexion.prepareStatement("INSERT INTO tbCocinero(UUID_Cocinero, Nombre, Edad, Peso, Correo) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            nuevoCocinero.setString(1, UUID.randomUUID().toString());
            nuevoCocinero.setString(2, getNombre());
            nuevoCocinero.setInt(3, getEdad());
            nuevoCocinero.setInt(4, getPeso());
            nuevoCocinero.setString(5, getCorreo());
 
            //Ejecutar la consulta
            nuevoCocinero.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
   
   public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Cocinero", "Nombre", "Edad", "Peso", "Correo"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbCocinero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Cocinero"), 
                    rs.getString("nombre"), 
                    rs.getInt("edad"), 
                    rs.getString("peso"),
                    rs.getString("correo")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
   
       public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbCocinero where UUID_Cocinero = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
       
        public void cargarDatosTabla(frmCocinero vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbCocinero.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbCocinero.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbCocinero.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbCocinero.getValueAt(filaSeleccionada, 2).toString();
            String pesodeTb = vista.jtbCocinero.getValueAt(filaSeleccionada, 3).toString();
            String correodeTb = vista.jtbCocinero.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(pesodeTb);
            vista.txtCorreo.setText(correodeTb);
        }
    }
     
        public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbCocinero set nombre= ?, edad = ?, peso = ?, correo  = ? where UUID_Cocinero = ?");

                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setInt(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
        
        
      public void limpiar(frmCocinero vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtCorreo.setText("");
        vista.txtPeso.setText("");
    }
    
}
