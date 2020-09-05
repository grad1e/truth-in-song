package dev.rtrilia.truthinsong.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rtrilia.truthinsong.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.database.entities.TopicEntity

@Database(
    entities = [MalayalamEntity::class, EnglishEntity::class, ResponsiveEntity::class, TopicEntity::class],
    version = 2,
    exportSchema = false
)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songBookDao(): SongBookDao
}
