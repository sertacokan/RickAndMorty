package com.example.rickandmortyapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "CharacterTable")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val characterId: Int,
    @ColumnInfo(name = "Name")
    val characterName: String,
    @ColumnInfo(name = "Status")
    val characterStatus: String,
    @ColumnInfo(name = "Species")
    val characterSpecies: String,
    @ColumnInfo(name = "Gender")
    val characterGender: String,
    @ColumnInfo(name = "ImageUrl")
    val characterImage: String,
    @ColumnInfo(name = "OriginLocationName")
    val characterOriginLocation: String,
    @ColumnInfo(name = "EpisodeCount")
    val numberOfEpisodes: Int,
    @ColumnInfo(name = "LastEpisodeName")
    val lastEpisode: String,
    @ColumnInfo(name = "LastEpisodeAirDate")
    val lastEpisodeAir: String,
    @ColumnInfo(name = "LastLocationName")
    val characterLastKnownLocation: String,
    @ColumnInfo(name = "FavoriteState")
    val isFavorite: Int = 0
) : Parcelable