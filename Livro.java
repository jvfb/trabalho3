package trab3;

public class Livro {
      private String titulo;
      private String tipo;
      private Integer isbn;
      private String autor;
      private String editora;
      private String status;

      public Livro(){

      }
      public Livro(String csv) {
        String[] values = csv.split(",");
        titulo = values[0];
        tipo = values[1];
        isbn = Integer.valueOf(values[2]);
        autor = values[3];
        editora = values[4];
        status = values[5];
      }

      public Livro(String titulo, String tipo, Integer isbn, String autor,
        String editora, String status ){
          this.titulo = titulo;
          this.tipo = tipo;
          this.isbn = isbn;
          this.autor = autor;
          this.editora = editora;
          this.status = status;
      }

      public String getTitulo(){
        return titulo;
      }
      public String getTipo(){
        return tipo;
      }
      public Integer getIsbn(){
        return isbn;
      }
      public String getAutor(){
        return autor;
      }
      public String getEditora(){
        return editora;
      }
      public String getStatus(){
        return status;
      }

      public void setTitulo(String titulo){
        this.titulo = titulo;
      }
      public void setTipo(String tipo){
        this.tipo = tipo;
      }
      public void setIsbn(Integer isbn){
        this.isbn = isbn;
      }
      public void setAutor(String autor){
        this.autor = autor;
      }
      public void setEditora(String editora){
        this.editora = editora;
      }
      public void setStatus (String status){
        this.status = status;
      }

      public String toString() {
        return "Titulo:" + titulo + " Tipo:" + tipo + " ISBN:" + isbn + " Autor:" + autor + " Editora:" + editora
        + " Status:" + status;
      }

}
