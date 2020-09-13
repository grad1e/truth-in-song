package dev.rtrilia.truthinsong.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rtrilia.truthinsong.data.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.data.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.data.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.data.database.entities.TopicEntity

@Database(
    entities = [MalayalamEntity::class, EnglishEntity::class, ResponsiveEntity::class, TopicEntity::class],
    version = 4,
    exportSchema = false
)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songBookDao(): SongBookDao
}
