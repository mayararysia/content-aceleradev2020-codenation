package br.com.codenation.models;

public class CommonData {
    private Long id;
    private String nome;

    public CommonData() {
    }

    public CommonData(Long id, String nome, String className) {
        if(validateParamsRequired(id, nome, className)) {
            this.id = id;
            this.nome = nome;
        }
    }

    private boolean validateParamsRequired(Long id, String name,String className){
        if (id == null ||  id <= 0 || name == null)
            throw new  NullPointerException("Identificador de ".concat(className).concat(" inválido!"));
        if (name.isEmpty())
            throw new IllegalArgumentException("Nome de ".concat(className).concat(" inválido!"));
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
