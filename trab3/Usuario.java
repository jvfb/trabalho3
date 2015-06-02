package trab3;

public class Usuario{

   String name;
   String tipo;
   Integer codigo;
   String status;


  public Usuario(){}

  public Usuario(String csv) {
		String[] values = csv.split(",");
		name = values[0];
		tipo = values[1];
		codigo = Integer.valueOf(values[2]);
		status = values[3];
	}

  public Usuario(String name, String tipo, Integer codigo, String status){
    this.name = name;
    this.tipo = tipo;
    this.codigo = codigo;
    this.status = status;

  }

  public void setName(String name){
    this.name = name;
  }

  public void setTipo(String tipo){
    this.tipo = tipo;
  }

  public void setCodigo(Integer codigo){
    this.codigo = codigo;
  }
  public void setStatus(String status){
    this.status = status;
  }

  public String getName(){
    return name;
  }
  public String getTipo(){
    return tipo;
  }
  public Integer getCodigo(){
    return codigo;
  }
  public String getStatus(){
    return status;
  }

  public String toString() {
		return "Nome:"+ name + " Tipo:" + tipo + " Codigo:" + codigo + " Status:" + status;
	}
}
