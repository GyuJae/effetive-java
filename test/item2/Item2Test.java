package item2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class Item2Test {

    @Test
    @DisplayName("생성자에 매개변수가 많다면 빌더를 고려하라")
    void test1() {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .fat(100)
                .sodium(35)
                .carbohydrate(27)
                .build();

        assertInstanceOf(NutritionFacts.class, cocaCola);
    }

    @Test
    @DisplayName("빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기가 좋다.")
    void test2() {
        Pizza pizza1 = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .build();
        Pizza pizza2 = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM)
                .sauceInside()
                .build();

        assertInstanceOf(Pizza.class, pizza1);
        assertInstanceOf(Pizza.class, pizza2);
    }
}