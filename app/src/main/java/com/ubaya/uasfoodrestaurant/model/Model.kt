package com.ubaya.uasfoodrestaurant.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class Recipes {
    @ColumnInfo(name = "name")
    var name:String?,
    @ColumnInfo(name = "category")
    var category: String?,
    @ColumnInfo(name = "likes")
    var likes:Int?,
    @ColumnInfo(name = "poster")
    var poster:String?,
    @ColumnInfo(name = "public_stat")
    var public_stat:Int?
    ){
        @PrimaryKey(autoGenerate = true)
        var recipe_id:Int = 0
    }

    @Entity(tableName = "recipesdraft")
    data class RecipesDraft(
        var name:String?,
        var category: String?,
        var likes:Int?,
        var poster:String?
    ){
        @PrimaryKey(autoGenerate = true)
        var recipe_id:Int = 0


    }

    @Entity(tableName = "myrecipes")
    data class MyRecipes(
        var name:String?,
        var category: Int?,
        var likes:Int?,
        var poster:String?
    ){
        @PrimaryKey(autoGenerate = true)
        var recipe_id:Int = 0
    }

    @Entity(tableName = "ingredients")
    class Ingredients(
        var recipe_id_ing:Int?,
        var item:String?,
        var amount:String?
    ){
        @PrimaryKey(autoGenerate = true)
        var ingredient_id:Int? = 0
    }

    @Entity(tableName = "preparations")
    class Preparations(
        var recipe_id_prep:Int?,
        var step:Int?,
        var description:String?
    ){
        @PrimaryKey(autoGenerate = true)
        var preparation_id:Int? = 0
    }

    data class Recipe(
        val recipe_id:Int?,
        val name:String?,
        val category: String?,
        val likes:Int?,
        val poster:String?
    )

    class Grocery(
        val nama:String?,
        val harga:Int?,
        val jumlah:Int?,
        val gambar:String?
    )
}