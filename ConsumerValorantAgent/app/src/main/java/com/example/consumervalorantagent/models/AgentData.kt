package com.example.consumervalorantagent.models

data class AgentData(
    val uuid: String,
    val displayName: String,
    val description: String,
    val developerName: String,
    val releaseDate: String, // Pode ser um ZonedDateTime ou Instant dependendo do framework de serialização
    val characterTags: List<String>?, // Nulo no exemplo, mas tipado como lista de Strings se viesse preenchido
    val displayIcon: String,
    val displayIconSmall: String,
    val bustPortrait: String,
    val fullPortrait: String,
    val fullPortraitV2: String,
    val killfeedPortrait: String,
    val minimapPortrait: String,
    val homeScreenPromoTileImage: String?, // Nulo no exemplo
    val background: String,
    val backgroundGradientColors: List<String>,
    val assetPath: String,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val isAvailableForTest: Boolean,
    val isBaseContent: Boolean,
    val role: Role,
    val recruitmentData: Any?, // Nulo no exemplo, pode ser qualquer coisa (Any?)
    val abilities: List<Ability>,
    val voiceLine: Any? // Nulo no exemplo
)
