// POLIMOSFISMO POR HERANÇA 
//As principais vantagens do polimorfismo por herança nesse códio são:
//1.Possibilidade de adicionar novos tipos de animais mudar o método emitirSons.
//2.Alterar animais existentes sem alterar o método emitirSons.
//3. A capacidade de adicionar novas classes de animais sem alterar o método emitirSons.

import java.util.ArrayList;
import java.util.List;

// Classe base abstrata que define o resto do comportamento
abstract class Bicho {
    // Método abstrato a ser implementado pelas subclasses
    abstract void somDele();
}

// Classes concretas que herdam de Animal
class Urso extends Bicho {
    @Override
    // Implementação do método abstrato
    public void somDele() {
        System.out.println("URSO: GRRRR");
    }
}

class Leão extends Bicho {
    @Override
    public void somDele() {
        System.out.println("LEÃO: ROAR");
    }
}

class Porco extends Bicho {
    @Override
    public void somDele() {
        System.out.println("PORCO: OINC");
    }
}

public class SonsAnimais {
    public static void emitirSons(List<Bicho> bichos) {
        for (Bicho animal : bichos) {
            animal.somDele();
        }
    }

    public static void main(String[] args) {
        // Criando lista de animais
        List<Bicho> bichos = new ArrayList<>();
        bichos.add(new Urso());
        bichos.add(new Leão());
        bichos.add(new Porco());

        // Emitindo sons dinamicamente
        emitirSons(bichos);
    }
}
