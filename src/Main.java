import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );

        }

        long toYoung = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетние жители " + "= " + toYoung);

       List<String> man = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 27)
                .filter(x -> x.getSex() == Sex.Man)
                .map(Person::getFamily)
                .distinct() // Чтобы список не был очень большим
                .collect(Collectors.toList());
        System.out.println(man);

        List<Person> worker = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> (x.getSex()==Sex.Man ? x.getAge() < 65 : x.getAge() < 60))
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.print(worker);


    }

}

