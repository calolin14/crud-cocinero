package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Cocinero;
import vista.frmCocinero;

//3- Heredar de la clase que detecta las acciones
public class ctrlCocinero implements MouseListener{
    
    //1- Mandar a llamar a las otras capas (modelo y vista)
    private Cocinero modelo;
    private frmCocinero vista;
   
    //2- Crear el constructor
    public ctrlCocinero(Cocinero modelo, frmCocinero vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.btnAgregar.addMouseListener(this);   
        modelo.Mostrar(vista.jtbCocinero);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbCocinero.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnAgregar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso(Integer.parseInt(vista.txtPeso.getText()));
            modelo.setCorreo(vista.txtCorreo.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbCocinero);
        }
        
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbCocinero);
            modelo.Mostrar(vista.jtbCocinero);
        }
        
        if(e.getSource() == vista.jtbCocinero){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualizar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso(Integer.parseInt(vista.txtPeso.getText()));
            modelo.setCorreo(vista.txtCorreo.getText());
            
            modelo.Actualizar(vista.jtbCocinero);
            modelo.Mostrar(vista.jtbCocinero);
        }
        
        if(e.getSource() == vista.btnLimpiar){
            modelo.limpiar(vista);
        }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
