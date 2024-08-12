package com.qa;

import com.qa.optionalmodels.Car;
import com.qa.optionalmodels.Radio;
import com.qa.optionalmodels.Station;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to this Java Practical\n");

        com.qa.models.Car car = new com.qa.models.Car("Ford", "Fiesta", "Red");

        System.out.println("QUESTION: What might be problematic with the following code?\n");
//        Comment / Uncomment the following line of code
//        String radioStationName = car.getRadio().getStation().getName();
        System.out.println("String radioStationName = car.getRadio().getStation().getName();\n");

        System.out.println("ANSWER: The car might not have a radio and therefore there is no station or station name\nwhich results in a NullPointerException\n");

        System.out.println("POSSIBLE WORKAROUND: Add defensive checks to prevent null references\n");

        String stationName = "UNKNOWN";
        if(car != null){
            com.qa.models.Radio radio = car.getRadio();
            if(radio != null){
                com.qa.models.Station station = radio.getStation();
                if(station != null){
                    stationName = station.getName();
                }
            }
        }

        System.out.println("ISSUE: Lots of nested checks impact the readability and cleanliness of \nthe codebase and can be error prone\n");
        System.out.println("IMPROVEMENT: Use Optional<T>\n");

        creatingOptionals();

        usingOptionals();

        defaultValueOptionals();

        exceptionIfEmpty();

        filterOptionals();

        mapFunctionsToOptionals();

        flatMapFunctionsToOptionals();

    }

    public static void creatingOptionals(){
        System.out.println("CREATING OPTIONALS: There are several ways...\n");

        System.out.println("Here's an empty optional\n");
        Optional<Radio> emptyRadio = Optional.empty();

        System.out.println("Here's an optional with a non-null value\n");
        Radio radio = new Radio();
        Optional<Radio> nonEmptyRadio = Optional.of(radio);

        System.out.println("If radio was null an exception would be thrown immediately\nrather than waiting for you to access attributes of the null object\n");
        radio = null;
//        Optional<Radio> shouldNotBeEmptyRadio = Optional.of(radio);


        System.out.println("If radio might be null, use ofNullable\n");
        Optional<Radio> mightBeNullRadio = Optional.ofNullable(radio);


    }

    public static void usingOptionals() {
        System.out.println("Using the optional\n");

        System.out.println("Without an optional you need to do a null check...");
        Radio radio = new Radio();
        if(radio != null){
            System.out.println(radio);
        }

        System.out.println("USING: ifPresent");
        System.out.println("ifPresent is used to invoke the specified consumer with the value, otherwise do nothing\n");
        System.out.println("With an optional that is empty nothing is printed...\n");
        Optional<Radio> optionalRadio = Optional.empty();
        optionalRadio.ifPresent(System.out::println);

        System.out.println("With an optional that is not empty something is printed...");
        Optional<Radio> anotherOptionalRadio = Optional.of(radio);
        anotherOptionalRadio.ifPresent(System.out::println);

        System.out.println("USING: isPresent");
        System.out.println("isPresent returns true if there is a value present, otherwise false\n");

        System.out.println("isPresent can be combined with 'get' to return the value contained in the optional");

        if(anotherOptionalRadio.isPresent()){
            System.out.println(anotherOptionalRadio.get());
        }

        System.out.println("NOTE: This is not much of an improvement over nested null checks \nthough so better options are available.\n");

    }

    public static void defaultValueOptionals(){
        System.out.println("OR ELSE: orElse can be used to provide a default value if the optional is empty");

        System.out.println("This car has a radio so orElse is ignored:");
        Car carWithRadio = new Car("Ford", "Fiesta", "Red");
        Radio radio = new Radio();
        carWithRadio.setRadio(Optional.of(radio));

        Radio radioDetails = carWithRadio.getRadio().orElse(new Radio("High-end radio"));
        System.out.println(radioDetails.getType());

        System.out.println("\nThis car does not have a radio so orElse is used to provide a default value:");
        Car carWithoutRadio = new Car("Ford", "Focus", "Black");
        radio = null;
        carWithoutRadio.setRadio(Optional.ofNullable(radio));

        Radio radioDetails2 = carWithoutRadio.getRadio().orElse(new Radio("Custom stereo"));
        System.out.println(radioDetails2.getType());

    }

    public static void exceptionIfEmpty(){
        System.out.println("\nOR ELSE THROW: orElseThrow can be used to throw an exception if the optional is empty\n");

        System.out.println("This car has an engine:");
        Car car = new Car("Audi", "A8", "Silver");
        car.setEngine(Optional.of("3.0 L V6 Electric"));

        String engine = car.getEngine().orElseThrow(IllegalStateException::new);
        System.out.println(engine);

        System.out.println("\nThe car has no engine so an exception is thrown:");

        car.setEngine(Optional.empty());

//       uncomment / comment the following two lines of code
//        engine = car.getEngine().orElseThrow(IllegalStateException::new);
//        System.out.println(engine);
    }

    public static void filterOptionals(){
        System.out.println("\nFILTER: filter is used to compare a present value to a predicate and return an \noptional describing the value, or return an empty optional\n");
        Optional<Radio> radio = Optional.of(new Radio());

        Optional<Station> station = Optional.of(new Station("BBC Radio 1"));
        radio.get().setStation(station);

        System.out.println("This filter predicate is a match so returns an optional:");
        Optional<Station> maybeStation = station.filter(s -> "BBC Radio 1".equals(s.getName()));
        maybeStation.ifPresent(System.out::println);

        System.out.println("\nThis filter predicate is NOT a match so returns an empty optional:");
        maybeStation = station.filter(s -> "Smooth Radio".equals(s.getName()));
        maybeStation.ifPresent(System.out::println);


//        radio.filter(r -> "BBC Radio 1".equals(r.getStation().get().getName()).if(()-> System.out.println("It's Radio 1")));
//        radio.ifPresent(radio.filter(r -> "BBC Radio 1".equals(r.getStation().get().getName())), System.out::println);

    }

    public static void mapFunctionsToOptionals(){
        System.out.println("\nMAP: if a value is present, apply the provided mapping function to it,\nand if the result is non-null, return an Optional describing the result,\notherwise return an empty Optional\n");

        Optional<Radio> radio = Optional.of(new Radio());

        Optional<Station> station = Optional.of(new Station("BBC Radio 1"));
        radio.get().setStation(station);

        System.out.println("This maps the getStation method to the Radio, 'transforming' it into a Station if it is not empty.");
        System.out.println("The FILTER is then applied to the station and if a non-empty optional is returned, it is printed:");
        radio.map(Radio::getStation)
                .filter(s -> "BBC Radio 1".equals(s.get().getName()))
                .ifPresent((r) -> System.out.println("It's a BBC station"));

        System.out.println("\nThis maps the getStation method to the Radio, 'transforming' it into a Station if it is not empty.");
        System.out.println("The FILTER is then applied to the station and because an empty optional is returned, nothing is printed.\n");
        radio.map(Radio::getStation)
                .filter(s -> "Smooth Radio".equals(s.get().getName()))
                .ifPresent((r) -> System.out.println("It's not a BBC station"));

    }

    public static void flatMapFunctionsToOptionals(){
        System.out.println("FLAT MAP: flatMap does not wrap the result in an additional optional.\n");

        Optional<Car> car = Optional.of(new Car("Audi", "A8", "Silver"));
        car.get().setEngine(Optional.of("3.0 L V6 Electric"));
        car.get().setRadio(Optional.of(new Radio("Rogue Acoustics Audio System")));
        car.get().getRadio().get().setStation(Optional.of(new Station("Heart FM")));

        String stationName = car.flatMap(Car::getRadio)//this returns an Optional<Radio> rather than an Optional<Optional<Radio>>
                                .flatMap(Radio::getStation)//this returns an Optional<Station> rather than an Optional<Optional<Station>>
                .map(Station::getName)
                .orElse("Radio isn't tuned to a station");

        System.out.println(stationName);
    }
}