package com.example.yu.team_project_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class to create a new database containing two tables
 * one table for the pre-loaded items, and
 * another table for the shopping list.
 */
public class K_DatabaseCreator extends SQLiteOpenHelper {

    protected static final String ACTIVITY_NAME = "K_DatabaseCreator";

    private static final String DATABASE_NAME = "ShoppingDatabase.db";
    private static final int VERSION_NUM = 1;

    ArrayList<String> items1 = new ArrayList<>();
    ArrayList<String> items2 = new ArrayList<>();

    public static final String KEY_MESSAGE = "Item_Name";
    public static final String KEY_MESSAGE2 = "Item_Icon";
    public static final String KEY_CART1 = "Name";
    public static final String KEY_CART2 = "Icon";

    public K_DatabaseCreator(Context ctx) {
        super(ctx,DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Items (Item_Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Item_Name TEXT, Item_Icon TEXT);");
        db.execSQL("CREATE TABLE Cart (Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Name TEXT, Icon TEXT);");
        Log.i(ACTIVITY_NAME,"On Create");

        /* Put initial items in the database */
        databaseFiller();
        for ( int i = 0; i < items1.size();i++){
            db.execSQL("INSERT INTO Items (Item_Name, Item_Icon) VALUES ('"+items1.get(i)+ "','"
                    + items2.get(i) + "');");
            Log.i(ACTIVITY_NAME,"Inserting" + items1.get(i));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Items;");
        db.execSQL("DROP TABLE IF EXISTS Cart;");
        this.onCreate(db);

        Log.i(ACTIVITY_NAME, "Calling onUpgrade from version "
                + oldVersion + " to " + newVersion );
    }

    /* Includes all the items in the database */
    public void databaseFiller() {

        items1.add("Apples");           items2.add("k_apples");
        items1.add("Asparagus");        items2.add("k_asparagus");
        items1.add("Avocado");          items2.add("k_avocado");
        items1.add("Bacon");            items2.add("k_bacon");
        items1.add("Bananas");          items2.add("k_bananas");
        items1.add("Batteries");        items2.add("k_battery");
        items1.add("Beans");            items2.add("k_beans");
        items1.add("Beer");             items2.add("k_beer");
        items1.add("Beets");            items2.add("k_beet");
        items1.add("BirthDay Cake");    items2.add("k_bdaycake");
        items1.add("Water");            items2.add("k_bottlewater");
        items1.add("Bread");            items2.add("k_bread");
        items1.add("Broccoli");         items2.add("k_broccoli");
        items1.add("Butter");           items2.add("k_butter");
        items1.add("Cabbage");          items2.add("k_cabbage");
        items1.add("Carrots");          items2.add("k_carrots");
        items1.add("Cheese");           items2.add("k_cheese");
        items1.add("Cherries");         items2.add("k_cherries");
        items1.add("Chicken");          items2.add("k_chicken");
        items1.add("Chicken Broth");    items2.add("k_broth");
        items1.add("Chips");            items2.add("k_nachos");
        items1.add("Cinnamon Rolls");   items2.add("k_cinnamonroll");
        items1.add("Coca Cola");        items2.add("k_cocacola");
        items1.add("Coffee");           items2.add("k_coffee");
        items1.add("Cookies");          items2.add("k_cookies");
        items1.add("Corn");             items2.add("k_corn");
        items1.add("Cucumber");         items2.add("k_cucumber");
        items1.add("Deodorant");        items2.add("k_deodorant");
        items1.add("Dish Soap");        items2.add("k_liquidsoap");
        items1.add("Eggs");             items2.add("k_egg");
        items1.add("Fish");             items2.add("k_fish");
        items1.add("Flour");            items2.add("k_flour");
        items1.add("Garbage Bags");     items2.add("k_garbagebag");
        items1.add("Garlic");           items2.add("k_garlic");
        items1.add("Grapes");           items2.add("k_grapes");
        items1.add("Ham");              items2.add("k_ham");
        items1.add("Hand Soap");        items2.add("k_soapdispenser");
        items1.add("Honey");            items2.add("k_honey");
        items1.add("Hot Dogs");         items2.add("k_hotdog");
        items1.add("Hot Peppers");      items2.add("k_chilipepper");
        items1.add("Ice Cream");        items2.add("k_icecream");
        items1.add("Jam");              items2.add("k_jam");
        items1.add("Ketchup");          items2.add("k_ketchup");
        items1.add("Kitty Food");       items2.add("k_catcolor");
        items1.add("Kitty Litter");     items2.add("k_catblack");
        items1.add("Kitty Treats");     items2.add("k_catfootprint");
        items1.add("Kiwi");             items2.add("k_kiwi");
        items1.add("Laundry Detergent");items2.add("k_detergent");
        items1.add("Lemons");           items2.add("k_lemon");
        items1.add("Lettuce");          items2.add("k_lettuce");
        items1.add("Light Bulbs");      items2.add("k_lightbulb");
        items1.add("Lobster");          items2.add("k_lobster");
        items1.add("Meat");             items2.add("k_steak");
        items1.add("Milk");             items2.add("k_milk");
        items1.add("Mushrooms");        items2.add("k_mushrooms");
        items1.add("Mustard");          items2.add("k_mustard");
        items1.add("Napkins");          items2.add("k_napkin");
        items1.add("Noodles");          items2.add("k_noodles");
        items1.add("Olive Oil");        items2.add("k_oliveoil");
        items1.add("Onions");           items2.add("k_onion");
        items1.add("Orange Juice");     items2.add("k_orangejuice");
        items1.add("Oranges");          items2.add("k_orange");
        items1.add("Paper Towels");     items2.add("k_towel");
        items1.add("Pasta");            items2.add("k_spaghetti");
        items1.add("Peameal");          items2.add("k_pork");
        items1.add("Pears");            items2.add("k_pears");
        items1.add("Peppercorn");       items2.add("k_peppercorn");
        items1.add("Sweet Peppers");    items2.add("k_peppers");
        items1.add("Pineapple");        items2.add("k_pineapple");
        items1.add("Pop Corn");         items2.add("k_popcorn");
        items1.add("Potatoes");         items2.add("k_potatoes");
        items1.add("Razors");           items2.add("k_razor");
        items1.add("Rice");             items2.add("k_rice");
        items1.add("Salt");             items2.add("k_salt");
        items1.add("Sausages");         items2.add("k_sausages");
        items1.add("Shrimp");           items2.add("k_shrimp");
        items1.add("Soy Sauce");        items2.add("k_soy");
        items1.add("Spaghetti");        items2.add("k_spaghetti");
        items1.add("Strawberries");     items2.add("k_strawberries");
        items1.add("Sugar");            items2.add("k_sugar");
        items1.add("Taco Sauce");       items2.add("k_taco");
        items1.add("Tissue Paper");     items2.add("k_tissue");
        items1.add("Toast Bread");      items2.add("k_toaster");
        items1.add("Toilet Paper");     items2.add("k_toiletpaper");
        items1.add("Tomatoes");         items2.add("k_tomatoes");
        items1.add("Tooth Paste");      items2.add("k_toothbrush");
        items1.add("Tortillas");        items2.add("k_tortillas");
        items1.add("Tuna");             items2.add("k_tuna");
        items1.add("Watermelon");       items2.add("k_watermelon");
        items1.add("Wheat");            items2.add("k_wheat");
        items1.add("Wine");             items2.add("k_wine");
        items1.add("Pork Shops");       items2.add("k_pork");
    }

} // *-* End of file *-*
