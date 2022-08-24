package com.revdex.crypto.domain.model

import com.revdex.crypto.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val team: List<TeamMember>,
    val tags: List<String>,
    val description: String,
)
