package com.example.lunchtray.datasource

import com.example.lunchtray.model.MenuItem.AccompanimentItem
import com.example.lunchtray.model.MenuItem.EntreeItem
import com.example.lunchtray.model.MenuItem.SideDishItem

object DataSource {

    val entreeMenuItems = listOf(
        EntreeItem(
            name = "Цветная капуста",
            description = "Хорошо прожаренная цветная капуста",
            price = 7.00,
        ),
        EntreeItem(
            name = "Чили три боба",
            description = "Черная и красная фасоль, приготовленная на медленном огне, посыпанная луком",
            price = 4.00,
        ),
        EntreeItem(
            name = "Грибная паста",
            description = "Паста Пенне, грибы, базилик, с помидорами тушеными в чесночном соусе" +
                    "и оливковом масле",
            price = 5.50,
        ),
        EntreeItem(
            name = "Сковородка острой черной фасоли",
            description = "Сезонные овощи, черная фасоль, авокадо и маринованы лук",
            price = 5.50,
        ),
        EntreeItem(
            name = "Кастрюля пельменей",
            description = "Вареные сибирские пельмени со сметаной",
            price = 15.50,
        )
    )

    val sideDishMenuItems = listOf(
        SideDishItem(
            name = "Летний салат",
            description = "помидоры, маслянистый салат, персики, авокадо, бальзамический уксус",
            price = 2.50,
        ),
        SideDishItem(
            name = "Сливочный суп из тыквы",
            description = "тыква, обжаренный перец, масло чили",
            price = 3.00,
        ),
        SideDishItem(
            name = "Острый картофель",
            description = "Мраморный картофель, запеченый и обжаренный в домашних специях",
            price = 2.00,
        ),
        SideDishItem(
            name = "Рис в кокосовом соку",
            description = "Рис, кокосовое молоко, лайм и сахар",
            price = 1.50,
        )
    )

    val accompanimentMenuItems = listOf(
        AccompanimentItem(
            name = "Булочка",
            description = "Свежеиспеченная булочка",
            price = 0.50,
        ),
        AccompanimentItem(
            name = "Ягодная смесь",
            description = "Клубника, голубика, малина и черника",
            price = 1.00,
        ),
        AccompanimentItem(
            name = "Маринованные овощи",
            description = "Домашние маринованые огурцы и морковь",
            price = 0.50,
        )
    )
}
