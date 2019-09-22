package com.gorkem.githubrepo.data.local.favourite

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


@Entity(tableName = "favourite")
open class Favourite(
    @PrimaryKey
    @ColumnInfo(name = "repoUniqueId")
    var id: Int
)