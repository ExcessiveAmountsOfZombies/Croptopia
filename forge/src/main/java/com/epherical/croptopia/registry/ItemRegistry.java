package com.epherical.croptopia.registry;

import com.epherical.croptopia.common.ItemNames;
import com.epherical.croptopia.items.CookingUtensil;
import com.epherical.croptopia.items.CropItem;
import com.epherical.croptopia.items.CroptopiaSaplingItem;
import com.epherical.croptopia.items.Drink;
import com.epherical.croptopia.items.GuideBookItem;
import com.epherical.croptopia.items.SeedItem;
import com.epherical.croptopia.items.Soup;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegistryEvent;

import static com.epherical.croptopia.common.ItemNames.*;
import static com.epherical.croptopia.CroptopiaForge.*;
import static com.epherical.croptopia.registry.FoodRegistry.*;
import static net.minecraft.world.level.biome.Biome.BiomeCategory.*;

public class ItemRegistry {
    // Fruits & Vegetables // cropitem
    public static Item artichoke;
    public static Item asparagus;
    public static Item barley;
    public static Item bellPepper;
    public static Item blackBean;
    public static Item blackberry;
    public static Item blueberry;
    public static Item broccoli;
    public static Item cabbage;
    public static Item cantaloupe;
    public static Item cauliflower;
    public static Item celery;
    public static Item coffeeBeans;
    public static Item corn;
    public static Item cranberry;
    public static Item cucumber;
    public static Item currant;
    public static Item eggplant;
    public static Item elderberry;
    public static Item garlic;
    public static Item grape;
    public static Item greenBean;
    public static Item greenOnion;
    public static Item honeydew;
    public static Item hops;
    public static Item kale;
    public static Item kiwi;
    public static Item leek;
    public static Item lettuce;
    public static Item oat;
    public static Item olive;
    public static Item onion;
    public static Item peanut;
    public static Item pineapple;
    public static Item radish;
    public static Item raspberry;
    public static Item rhubarb;
    public static Item rice;
    public static Item rutabaga;
    public static Item saguaro;
    public static Item soybean;
    public static Item spinach;
    public static Item squash;
    public static Item strawberry;
    public static Item sweetPotato;
    public static Item tomatillo;
    public static Item tomato;
    public static Item turnip;
    public static Item yam;
    public static Item zucchini;

    // Trees
    public static Item orange;
    public static Item banana;
    public static Item persimmon;
    public static Item plum;
    public static Item cherry;
    public static Item lemon;
    public static Item grapefruit;
    public static Item kumquat;
    public static Item peach;
    public static Item coconut;
    public static Item nutmeg;
    public static Item fig;
    public static Item nectarine;
    public static Item mango;
    public static Item dragonFruit;
    public static Item starFruit;
    public static Item avocado;
    public static Item apricot;
    public static Item pear;
    public static Item lime;
    public static Item date;
    public static Item almond;
    public static Item cashew;
    public static Item pecan;
    public static Item walnut;

    // Spices
    public static Item mustard;
    public static Item vanilla;
    public static Item paprika;
    public static Item chilePepper;
    public static Item salt;
    public static Item turmeric;
    public static Item ginger;
    // Herbs
    public static Item basil;


    public static Item artichokeSeed;
    public static Item asparagusSeed;
    public static Item bellPepperSeed;
    public static Item blackBeanSeed;
    public static Item blackberrySeed;
    public static Item blueberrySeed;
    public static Item broccoliSeed;
    public static Item cabbageSeed;
    public static Item cantaloupeSeed;
    public static Item cauliflowerSeed;
    public static Item celerySeed;
    public static Item coffeeSeed;
    public static Item cornSeed;
    public static Item cranberrySeed;
    public static Item cucumberSeed;
    public static Item currantSeed;
    public static Item eggplantSeed;
    public static Item elderberrySeed;
    public static Item garlicSeed;
    public static Item grapeSeed;
    public static Item greenBeanSeed;
    public static Item greenOnionSeed;
    public static Item honeydewSeed;
    public static Item hopsSeed;
    public static Item kaleSeed;
    public static Item kiwiSeed;
    public static Item leekSeed;
    public static Item lettuceSeed;
    public static Item oliveSeed;
    public static Item onionSeed;
    public static Item peanutSeed;
    public static Item pineappleSeed;
    public static Item radishSeed;
    public static Item raspberrySeed;
    public static Item rhubarbSeed;
    public static Item riceSeed;
    public static Item rutabagaSeed;
    public static Item saguaroSeed;
    public static Item spinachSeed;
    public static Item squashSeed;
    public static Item strawberrySeed;
    public static Item sweetPotatoSeed;
    public static Item tomatilloSeed;
    public static Item tomatoSeed;
    public static Item turnipSeed;
    public static Item yamSeed;
    public static Item zucchiniSeed;
    public static Item mustardSeed;
    public static Item chilePepperSeed;
    public static Item turmericSeed;
    public static Item gingerSeed;
    public static Item basilSeed;
    public static Item oatSeed;
    public static Item barleySeed;
    public static Item soybeanSeed;
    public static Item vanillaSeed;



    public static Item appleSapling;
    public static Item bananaSapling;
    public static Item orangeSapling;
    public static Item persimmonSapling;
    public static Item plumSapling;
    public static Item cherrySapling;
    public static Item lemonSapling;
    public static Item grapefruitSapling;
    public static Item kumquatSapling;
    public static Item peachSapling;
    public static Item coconutSapling;
    public static Item nutmegSapling;
    public static Item figSapling;
    public static Item nectarineSapling;
    public static Item mangoSapling;
    public static Item dragonFruitSapling;
    public static Item starFruitSapling;
    public static Item avocadoSapling;
    public static Item apricotSapling;
    public static Item pearSapling;
    public static Item limeSapling;
    public static Item dateSapling;
    public static Item almondSapling;
    public static Item cashewSapling;
    public static Item pecanSapling;
    public static Item walnutSapling;

    // secondary ingredients?
    public static Item oliveOil;
    public static Item cheese;
    public static Item flour;
    public static Item butter;
    public static Item noodle;
    public static Item tofu;
    public static Item molasses;
    public static Item caramel;
    public static Item chocolate;
    public static Item tortilla;
    public static Item soySauce;
    public static Item dough;
    public static Item ravioli;
    public static Item salsa;
    public static Item artichokeDip;
    public static Item pepperoni;

    // drinks
    public static Item grapeJuice;
    public static Item orangeJuice;
    public static Item appleJuice;
    public static Item cranberryJuice;
    public static Item saguaroJuice;
    public static Item tomatoJuice;
    public static Item melonJuice;
    public static Item pineappleJuice;
    public static Item coffee;
    public static Item lemonade;
    public static Item limeade;
    public static Item soyMilk;
    //public static Item tea;

    public static Item strawberrySmoothie;
    public static Item bananaSmoothie;
    public static Item kaleSmoothie;
    public static Item fruitSmoothie;

    public static Item chocolateMilkshake;

    public static Item beer;
    public static Item wine;
    public static Item mead;
    public static Item rum;
    public static Item pumpkinSpiceLatte;

    // jams
    public static Item grapeJam;
    public static Item strawberryJam;
    public static Item peachJam;
    public static Item apricotJam;
    public static Item blackberryJam;
    public static Item blueberryJam;
    public static Item cherryJam;
    public static Item elderberryJam;
    public static Item raspberryJam;

    // snacks?
    public static Item beefJerky;
    public static Item porkJerky;
    public static Item kaleChips;
    public static Item potatoChips;
    public static Item steamedRice;
    public static Item frenchFries;
    public static Item sweetPotatoFries;
    public static Item onionRings;
    public static Item raisins;
    public static Item doughnut;
    public static Item popcorn;
    public static Item bakedBeans;
    public static Item toast;
    public static Item cucumberSalad;
    public static Item caesarSalad;
    public static Item leafySalad;
    public static Item fruitSalad;
    public static Item veggieSalad;
    public static Item porkAndBeans;
    public static Item oatmeal;
    public static Item leekSoup;
    public static Item yoghurt;
    public static Item saucyChips;
    public static Item roastedNuts;
    public static Item trailMix;
    public static Item proteinBar;
    public static Item nougat;

    // breakfast
    public static Item scrambledEggs;
    public static Item butteredToast;
    public static Item toastWithJam;


    // meals
    public static Item hamSandwich;
    public static Item peanutButterAndJam;
    public static Item BLT;
    public static Item grilledCheese;
    public static Item tunaSandwich;
    public static Item cheeseburger;
    public static Item hamburger;
    public static Item tofuBurger;
    public static Item pizza;
    public static Item supremePizza;
    public static Item cheesePizza;
    public static Item pineapplePepperoniPizza;
    public static Item lemonChicken;
    public static Item friedChicken;
    public static Item chickenAndNoodles;
    public static Item chickenAndDumplings;
    public static Item tofuAndDumplings;
    public static Item spaghettiSquash;
    public static Item chickenAndRice;
    public static Item taco;
    public static Item sushi;
    public static Item eggRoll;
    public static Item cashewChicken;

    // desert block?
    public static Item coffeeCake;
    public static Item chocolateCake;
    //public static Item fruitCake;
    public static Item strawberryShortCake;
    public static Item carrotCake;
    public static Item turtleCake;

    // desert item
    public static Item applePie;
    public static Item yamJam;
    public static Item bananaCreamPie;
    public static Item candyCorn;
    public static Item vanillaIceCream;
    public static Item strawberryIceCream;
    public static Item mangoIceCream;
    public static Item rumRaisinIceCream;
    public static Item pecanIceCream;
    public static Item cherryPie;
    public static Item cheeseCake;
    public static Item brownies;
    public static Item snickerDoodle;
    public static Item bananaNutBread;
    public static Item pecanPie;
    public static Item candiedNuts;
    public static Item almondBrittle;
    public static Item oatmealCookie;
    public static Item nuttyCookie;
    public static Item praline;

    public static Item burrito;
    public static Item tostada;
    public static Item horchata;
    public static Item carnitas;
    public static Item fajitas;
    public static Item enchilada;
    public static Item churros;
    public static Item tamales;
    public static Item tresLecheCake;
    public static Item stuffedPoblanos;
    public static Item chiliRelleno;
    public static Item crema;
    public static Item refriedBeans;
    public static Item chimichanga;
    public static Item quesadilla;

    public static Item cinnamon;
    public static Item cornHusk;
    public static Item whippingCream;
    public static Item pepper;
    public static Item vanillaSeeds;

    public static Item cinnamonSapling;
    public static Item cinnamonLog;
    public static Item strippedCinnamonLog;
    public static Item cinnamonWood;
    public static Item strippedCinnamonWood;

    // 1.4.0
    public static Item shepherdsPie;
    public static Item beefWellington;
    public static Item fishAndChips;
    public static Item etonMess;
    public static Item tea;
    public static Item cornishPasty;
    public static Item scones;
    public static Item figgyPudding;
    public static Item treacleTart;
    public static Item stickyToffeePudding;
    public static Item trifle;
    public static Item pepperSeed;
    public static Item waterBottle;
    public static Item milkBottle;
    public static Item teaLeaves;
    public static Item teaSeed;

    // 1.7.0
    public static Item ajvar;
    public static Item ajvarToast;
    public static Item avocadoToast;
    public static Item bakedSweetPotato;
    public static Item bakedYam;
    public static Item beefStew;
    public static Item beefStirFry;
    public static Item butteredGreenBeans;
    public static Item cheesyAsparagus;
    public static Item chocolateIceCream;
    public static Item cookedBacon;
    public static Item eggplantParmesan;
    public static Item fruitCake;
    public static Item grilledEggplant;
    public static Item kiwiSorbet;
    public static Item knife;
    public static Item lemonCoconutBar;
    public static Item netherWartStew;
    public static Item peanutButter;
    public static Item peanutButterWCelery;
    public static Item potatoSoup;
    public static Item ratatouille;
    public static Item rawBacon;
    public static Item rhubarbCrisp;
    public static Item roastedAsparagus;
    public static Item roastedRadishes;
    public static Item roastedSquash;
    public static Item roastedTurnips;
    public static Item steamedBroccoli;
    public static Item steamedGreenBeans;
    public static Item stirFry;
    public static Item stuffedArtichoke;
    public static Item toastSandwich;

    //1.9.0
    public static Item roastedPumpkinSeeds;
    public static Item roastedSunflowerSeeds;
    public static Item pumpkinBars;
    public static Item cornBread;
    public static Item pumpkinSoup;
    public static Item meringue;
    public static Item cabbageRoll;
    public static Item borscht;
    public static Item goulash;
    public static Item beetrootSalad;
    public static Item candiedKumquats;
    public static Item shrimp;
    public static Item tuna;
    public static Item calamari;
    public static Item crab;
    public static Item roe;
    public static Item clam;
    public static Item oyster;
    public static Item cookedShrimp;
    public static Item cookedTuna;
    public static Item cookedCalamari;
    public static Item steamedCrab;
    public static Item glowingCalamari;
    public static Item seaLettuce;
    public static Item deepFriedShrimp;
    public static Item tunaRoll;
    public static Item friedCalamari;
    public static Item crabLegs;
    public static Item steamedClams;
    public static Item grilledOysters;
    public static Item anchovy;
    public static Item cookedAnchovy;
    public static Item anchovyPizza;
    public static Item mashedPotatoes;

    // cooking utensils?
    public static Item foodPress;
    public static Item fryingPan;
    public static Item cookingPot;
    public static Item mortarAndPestle;

    public static Item saltOre;



    public static void init(RegistryEvent.Register<Item> itemRegister) {

    }

    public static Item.Properties createGroup() {
        return new Item.Properties().tab(CROPTOPIA_ITEM_GROUP);
    }
}
