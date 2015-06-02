package trab3;

import java.io.*;
import java.util.*;
import java.text.*;

public class Biblioteca{


  List<Livro> livros;
  List<Emprestimo> emprestimos;
  List<Usuario> usuarios;

  static GregorianCalendar cal;

  Scanner input = new Scanner(System.in);

  public void mainMenu(){
     menu:while(true) {
        System.out.println("SISTEMA DE BIBLIOTECA: \n" +
        "  1) Cadastros\n" +
        "  2) Listar\n" +
        "  3) Emprestimos\n"+
        "  4) Sair\n");

        int selection = input.nextInt();
        input.nextLine();

         switch (selection) {
           case 1:
             this.cadastrarMenu();
             break;
           case 2:
             this.listarMenu();
             break;
          case 3:
            this.emprestarMenu();
            break;
          case 4:
            System.out.println("Saindo do programa...");
            break menu;
          default:
            System.out.println("Opção inválida.");
            break;
          }

    }



  }


  public void cadastrarMenu(){
        menu: while(true) {
              System.out.println("CADASTRAR: \n" +
              "  1) Livro\n" +
              "  2) Usuário\n" +
              "  3) Voltar\n");

            int selection = input.nextInt();
            input.nextLine();

             switch (selection) {
               case 1:

                 Livro livro = new Livro();
                 System.out.println("Digite o titulo do livro:");
                 livro.setTitulo(input.nextLine());
                 System.out.println("Tipo (Geral ou Texto):");
                 livro.setTipo(input.nextLine());
                 System.out.println("ISBN:");
                 livro.setIsbn(input.nextInt());
                 input.nextLine();
                 System.out.println("Autor:");
                 livro.setAutor(input.nextLine());
                 System.out.println("Editora:");
                 livro.setEditora(input.nextLine());
                 livro.setStatus("disponivel");
                 System.out.println("Livro cadastrado.");
                 livros.add(livro);
                 break;
               case 2:

                 Usuario usuario = new Usuario();
                 System.out.println("Digite o nome:");
                 usuario.setName(input.nextLine());
                 System.out.println("Tipo (Aluno, Professor ou Comunidade):");
                 usuario.setTipo(input.nextLine());
                 System.out.println("CODIGO:");
                 usuario.setCodigo(input.nextInt());
                 usuario.setStatus("ativo");
                 System.out.println("Usuario cadastrado.");
                 usuarios.add(usuario);
                 break;
              case 3:
                break menu;
              default:
                System.out.println("Opção inválida.");
                break;
              }

        }
  }



  public void listarMenu(){
        menu: while(true) {
            System.out.println("LISTAR: \n" +
                "  1) Livros\n" +
                "  2) Usuários\n" +
                "  3) Empréstimos\n" +
                "  4) Voltar\n");

            int selection = input.nextInt();
            input.nextLine();

             switch (selection) {
               case 1:
                 //this.cadastrarMenu();
                 for (int i = 0; i < livros.size(); i++)
                 {System.out.println( livros.get(i) );}

                 break;
               case 2:
                 //this.listarMenu();
                 for (int i = 0; i < usuarios.size(); i++)
                 {System.out.println( usuarios.get(i) );}
                 break;
              case 3:
              for (int i = 0; i < emprestimos.size(); i++)
                {System.out.println( emprestimos.get(i) );}
                  break;
              case 4:
                break menu;
              default:
                System.out.println("Opção inválida.");
                break;
              }

        }

  }




  public void emprestarMenu(){
    Livro l = new Livro();
    Usuario u = new Usuario();
    Integer isbn;
    Integer cod;

    menu: while(true) {
          System.out.println("CADASTRAR: \n" +
          "  1) Novo\n" +
          "  2) Devolução\n" +
          "  3) Voltar\n");

        int selection = input.nextInt();
        input.nextLine();

         switch (selection) {
           case 1:




             System.out.println("Digite o codigo do usuario): ");

             cod = input.nextInt();



             for(int i=0; i < usuarios.size(); i++){
               if (usuarios.get(i).getCodigo().equals(cod)){
                 if (usuarios.get(i).getStatus().equals("suspenso")){
                      System.out.println ("O usuário está suspenso e não pode fazer empréstimos.");
                      break menu;
                    }else{
                      u = usuarios.get(i);
                      break;
                      }

                 }
             }
             if ( u == null){
               System.out.println("Não foi possível concluir o emprestimo");
               break menu;
             }

             System.out.println("Digite o ISBN do livro:");
             isbn =  input.nextInt();
             input.nextLine();

             for(int i=0; i < livros.size(); i++){

               if (livros.get(i).getIsbn().equals(isbn)){
                     if( livros.get(i).getStatus().equals("indisponivel") ){
                        System.out.println ("O livro está indisponível para empréstimos.");
                        break menu;
                      }else{
                           l = livros.get(i);
                           livros.get(i).setStatus("indisponivel");
                           break;
                      }
                }
             }
             emprestimos.add(new Emprestimo(l, u, cal));
             System.out.println("Emprestimo cadastrado.");
             break;
           case 2:
             System.out.println("Digite o ISBN do livro:");
             isbn =  input.nextInt();
             input.nextLine();
             System.out.println("Digite o codigo do usuario): ");
             cod = input.nextInt();
             input.nextLine();

             for(int i=0; i < emprestimos.size() ; i++){

               if (emprestimos.get(i).usuario.getCodigo().equals(cod) &&
                   emprestimos.get(i).livro.getIsbn().equals(isbn)){

                     emprestimos.remove(emprestimos.get(i));
                     for(int j=0; j < livros.size(); j++){

                       if (livros.get(j).getIsbn().equals(isbn)){
                          livros.get(j).setStatus("disponivel");
                           break menu;
                         }
                      }
                }

            }
            break;
          case 3:
            break menu;
          default:
            System.out.println("Opção inválida.");
            break;
          }

    }



  }

  public void usuarioCsvReader(String arquivo) {
		this.usuarios = new ArrayList<Usuario>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(arquivo));
			String csv;
			while((csv = in.readLine()) != null) {
				usuarios.add(new Usuario(csv));
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File " + arquivo + " was not found!");
		}
		catch(IOException e) {
			System.out.println("Error reading the file!");
		}
	}

  public void usuarioCsvWriter(String arquivo){


    FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(arquivo);

            //Write a new student object list to the CSV file
            for (Usuario user : usuarios) {
                fileWriter.append(String.valueOf(user.getName()));
                fileWriter.append(",");
                fileWriter.append(user.getTipo());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(user.getCodigo()));
                fileWriter.append(",");
                fileWriter.append(user.getStatus());
                fileWriter.append("\n");
            }
            System.out.println("usuarios.csv escrito com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro de escrita em usuarios.csv.");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar usuarios.csv.");
                e.printStackTrace();
            }

        }

  }

  public void livroCsvReader(String arquivo) {
    this.livros = new ArrayList<Livro>();
    try {
      BufferedReader in = new BufferedReader(new FileReader(arquivo));
      String csv;
      while((csv = in.readLine()) != null) {
        livros.add(new Livro(csv));
      }
    }
    catch(FileNotFoundException e) {
      System.out.println("Arquivo " + arquivo + " não encontrado.");
    }
    catch(IOException e) {
      System.out.println("Erro ao ler o arquivo livros.csv");
    }
  }

  public void livroCsvWriter(String arquivo){


    FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(arquivo);

            //Write a new student object list to the CSV file
            for (Livro livro : livros) {
                fileWriter.append(livro.getTitulo());
                fileWriter.append(",");
                fileWriter.append(livro.getTipo());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(livro.getIsbn()));
                fileWriter.append(",");
                fileWriter.append(livro.getAutor());
                fileWriter.append(",");
                fileWriter.append(livro.getEditora());
                fileWriter.append(",");
                fileWriter.append(livro.getStatus());
                fileWriter.append("\n");
            }
            System.out.println("livros.csv escrito com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro de escrita em livros.csv.");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar livros.csv.");
                e.printStackTrace();
            }

        }

  }

  public void emprestimoCsvReader(String arquivo) {
    this.emprestimos = new ArrayList<Emprestimo>();
    try {
      BufferedReader in = new BufferedReader(new FileReader(arquivo));
      String csv;
      while((csv = in.readLine()) != null) {
        emprestimos.add(new Emprestimo(csv));
      }
    }
    catch(FileNotFoundException e) {
      System.out.println("Arquivo " + arquivo + " não foi encontrado.");
    }
    catch(IOException e) {
      System.out.println("Erro ao ler o arquivo emprestimos.csv");
    }
  }

  public void emprestimoCsvWriter(String arquivo){


    FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(arquivo);

            //Write a new student object list to the CSV file
            for (Emprestimo emprestimo : emprestimos) {
                fileWriter.append(emprestimo.livro.getTitulo());
                fileWriter.append(",");
                fileWriter.append(emprestimo.livro.getTipo());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(emprestimo.livro.getIsbn()));
                fileWriter.append(",");
                fileWriter.append(emprestimo.livro.getAutor());
                fileWriter.append(",");
                fileWriter.append(emprestimo.livro.getEditora());
                fileWriter.append(",");
                fileWriter.append(emprestimo.livro.getStatus());
                fileWriter.append(",");
                fileWriter.append(emprestimo.usuario.getName());
                fileWriter.append(",");
                fileWriter.append(emprestimo.usuario.getTipo());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(emprestimo.usuario.getCodigo()));
                fileWriter.append(",");
                fileWriter.append(emprestimo.usuario.getStatus());
                fileWriter.append("\n");
            }
            System.out.println("emprestimos.csv escrito com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro de escrita em emprestimos.csv. ");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar emprestimos.csv.");
                e.printStackTrace();
            }

        }

  }











  public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        Biblioteca b = new Biblioteca();
        try{
          System.out.println("Digite a data a ser considerada (DD MM AAAA):");
          DateFormat df = new SimpleDateFormat("dd MM yyyy");
          Date date = df.parse(in.nextLine());
          b.cal = new GregorianCalendar();
          cal.setTime(date);
          System.out.println("\n\n\nData atual: "+ cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR)+"\n\n\n");

        } catch (Exception e){}




       b.usuarioCsvReader("usuarios.csv");
       b.livroCsvReader("livros.csv");
       b.emprestimoCsvReader("emprestimos.csv");


      b.mainMenu();

       b.usuarioCsvWriter("usuarios.csv");
       b.livroCsvWriter("livros.csv");
       b.emprestimoCsvWriter("emprestimos.csv");



  }

}
