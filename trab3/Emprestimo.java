package trab3;

import java.util.*;

public class Emprestimo {

   Livro livro;
   Usuario usuario;
   GregorianCalendar inicio;
   GregorianCalendar fim;
   GregorianCalendar entrega;


  //
  // public Emprestimo(String csv) {
  //   String[] values = csv.split(",");
  //   livro.setTitulo = values[0];
  //   livro.setTipo = values[1];
  //   livro.setIsbn = Integer.valueOf(values[2]);
  //   livro.setAutor = values[3];
  //   livro.setEditora = values[4];
  //   livro.setStatus = values[5];
  //   usuario.setName = values[6];
  //   usuario.setTipo = values[7];
  //   usuario.setCodigo = Integer.valueOf(values[8]);
  //   usuario.setStatus = values[9];
  // }


  public Emprestimo(String csv) {
    String[] values = csv.split(",");
    this.livro = new Livro(values[0], values[1], Integer.valueOf(values[2]),
    values[3], values[4], values[5]);
    this.usuario = new Usuario( values[6], values[7],Integer.valueOf(values[8]),
    values[9]);
  }


  public Emprestimo(Livro livro, Usuario usuario, GregorianCalendar inicio){
    this.livro = livro;
    this.usuario = usuario;
    this.inicio = inicio;
    if (usuario.getTipo() == "professor")
      {this.fim = inicio;
      inicio.add(GregorianCalendar.DATE, 60);}
    else{
      this.fim = inicio;
      this.fim.add(GregorianCalendar.DATE, 15 );}
  }

  public void setLivro(Livro livro){
    this.livro = livro;
  }

  public void setUsuario(Usuario usuario){
    this.usuario = usuario;
  }

  public void setInicio(GregorianCalendar inicio){
    this.inicio = inicio;
  }

  public void setFim(GregorianCalendar fim){
    this.fim = fim;
  }

  public void setEntrega(GregorianCalendar entrega){
    this.entrega = entrega;
  }

  public Livro getLivro(){
    return this.livro;
  }

  public Usuario getUsuario(){
    return this.usuario;
  }

  public GregorianCalendar getInicio(){
    return this.inicio;
  }
  public GregorianCalendar getFim(){
    return this.fim;
  }

  public GregorianCalendar getEntrega(){
    return this.entrega;
  }

  public String toString() {
    return "ISBN:"+ livro.getIsbn() + " Usuario:" + usuario.getCodigo();
  }
}
