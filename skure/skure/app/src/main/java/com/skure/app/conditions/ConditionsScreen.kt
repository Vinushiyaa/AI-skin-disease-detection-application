package com.skure.app.conditions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

data class ConditionInfo(
    val label: String,
    val summary: String,
    val causes: List<String> = emptyList(),
    val otc: List<String> = emptyList(),
    val redFlags: List<String> = emptyList(),
    val diet: List<String> = emptyList(),
    val references: List<String> = emptyList()
)

val ConditionsData: Map<String, ConditionInfo> = mapOf(
    "eczema" to ConditionInfo(
        label = "Atopic dermatitis (Eczema)",
        summary = "Chronic inflammatory skin condition causing dry, itchy, inflamed patches.",
        causes = listOf("Genetic predisposition", "Skin barrier dysfunction", "Allergens/irritants"),
        otc = listOf("Fragrance-free emollients/moisturizers", "1% hydrocortisone for flares (short-term)", "Gentle cleansers"),
        redFlags = listOf("Severe infection signs (pus, fever)", "Extensive weeping lesions", "Infants with poor feeding"),
        diet = listOf("Regular hydration", "Avoid known triggers (harsh detergents, fragrances)", "Omega-3 rich foods"),
        references = listOf("AAD Guidelines on Atopic Dermatitis")
    ),
    "psoriasis" to ConditionInfo(
        label = "Plaque psoriasis",
        summary = "Autoimmune condition with well-demarcated, scaly plaques.",
        causes = listOf("Immune dysregulation", "Genetic factors", "Triggers: stress, infections"),
        otc = listOf("Coal tar shampoos/creams", "Keratolytics (salicylic acid)", "Moisturizers"),
        redFlags = listOf("Severe joint pain/swelling (psoriatic arthritis)", "Widespread erythroderma"),
        diet = listOf("Weight management", "Reduce alcohol", "Anti-inflammatory diet"),
        references = listOf("AAD Guidelines on Psoriasis")
    ),
    "tinea" to ConditionInfo(
        label = "Tinea (Ringworm)",
        summary = "Fungal infection causing ring-shaped, scaly, itchy patches.",
        causes = listOf("Dermatophyte fungi", "Warm humid environments", "Contact with infected persons/animals"),
        otc = listOf("Topical antifungals (clotrimazole, terbinafine)", "Keep area dry", "Avoid sharing towels"),
        redFlags = listOf("Spreading despite therapy", "Severe inflammation or fever"),
        diet = listOf("Keep skin dry", "Breathable clothing"),
        references = listOf("CDC Ringworm guidance")
    ),
    "acne" to ConditionInfo(
        label = "Acne vulgaris",
        summary = "Inflammatory disorder of the pilosebaceous unit with comedones, papules, pustules.",
        causes = listOf("Androgens", "Follicular hyperkeratinization", "Cutibacterium acnes", "Inflammation"),
        otc = listOf("Benzoyl peroxide 2.5–5%", "Adapalene 0.1% gel (topical retinoid)", "Non-comedogenic moisturizer"),
        redFlags = listOf("Nodulocystic lesions with scarring", "Sudden severe onset"),
        diet = listOf("Low glycemic load diet", "Limit dairy if sensitive"),
        references = listOf("AAD Acne Guidelines")
    ),
    "vitiligo" to ConditionInfo(
        label = "Vitiligo",
        summary = "Autoimmune depigmentation presenting as well-demarcated milky-white macules/patches.",
        causes = listOf("Autoimmune destruction of melanocytes", "Genetic predisposition"),
        otc = listOf("Broad-spectrum sunscreen SPF 30+", "Cosmetic camouflage"),
        redFlags = listOf("Rapid progression", "Perilesional inflammation"),
        diet = listOf("Sun protection", "Supportive counseling"),
        references = listOf("Vitiligo management resources")
    ),
    "urticaria" to ConditionInfo(
        label = "Urticaria (Hives)",
        summary = "Transient pruritic wheals with central pallor and surrounding erythema.",
        causes = listOf("Allergens, infections, medications", "Idiopathic"),
        otc = listOf("Non-sedating antihistamines (cetirizine, loratadine)", "Avoid triggers"),
        redFlags = listOf("Angioedema, breathing difficulty (seek emergency care)")
    ),
    "impetigo" to ConditionInfo(
        label = "Impetigo",
        summary = "Contagious superficial bacterial infection with honey-colored crusts.",
        causes = listOf("Staphylococcus aureus", "Streptococcus pyogenes"),
        otc = listOf("Gentle cleansing", "Topical antiseptics"),
        redFlags = listOf("Systemic symptoms (fever), spreading despite therapy")
    ),
    "rosacea" to ConditionInfo(
        label = "Rosacea",
        summary = "Chronic facial flushing, telangiectasia, papules/pustules; photosensitivity common.",
        causes = listOf("Vascular dysregulation", "Demodex mites", "Triggers: heat, alcohol"),
        otc = listOf("Gentle cleansers", "Sun protection"),
        diet = listOf("Avoid triggers: hot drinks, spicy foods, alcohol"),
        redFlags = listOf("Ocular rosacea (eye symptoms)")
    ),
    "contact_dermatitis" to ConditionInfo(
        label = "Contact dermatitis",
        summary = "Eczematous rash due to irritant or allergic exposure.",
        causes = listOf("Irritants (soaps, solvents)", "Allergens (nickel, fragrances)"),
        otc = listOf("Avoidance of trigger", "Topical hydrocortisone short-term"),
        redFlags = listOf("Extensive blistering, infection")
    ),
    "nevus" to ConditionInfo(
        label = "Melanocytic nevus (Mole)",
        summary = "Benign proliferation of melanocytes; stable symmetric pigmentation.",
        otc = listOf("Sun protection", "Self-monitor for ABCDE changes")
    ),
    "melanoma" to ConditionInfo(
        label = "Melanoma (Suspect)",
        summary = "Malignant melanoma suspicion warrants urgent specialist review.",
        redFlags = listOf("ABCDE changes: Asymmetry, Border irregularity, Color variation, Diameter >6mm, Evolving"),
        otc = listOf("Do not delay specialist assessment"),
        references = listOf("NCCN Melanoma Guidelines")
    )
)

@Composable
fun ConditionsScreen() {
    val viewModel: ConditionsViewModel = hiltViewModel()
    var selectedKey by remember { mutableStateOf<String?>(null) }
    val scrollList = rememberScrollState()
    val scrollDetails = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.45f)
                .fillMaxHeight()
                .verticalScroll(scrollList)
        ) {
            ConditionsData.forEach { (key, info) ->
                Card(
                    onClick = { 
                        selectedKey = key
                        // Save condition to database when viewed
                        viewModel.saveConditionViewed(key)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(info.label, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(info.summary, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(0.55f)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                .padding(12.dp)
                .verticalScroll(scrollDetails)
        ) {
            val info = selectedKey?.let { ConditionsData[it] }
            if (info == null) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Select a condition to view details")
                }
            } else {
                Text(info.label, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text(info.summary)
                if (info.causes.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Text("Causes", style = MaterialTheme.typography.titleMedium)
                    info.causes.forEach { Text("• $it") }
                }
                if (info.otc.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Text("Care / OTC", style = MaterialTheme.typography.titleMedium)
                    info.otc.forEach { Text("• $it") }
                }
                if (info.redFlags.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Text("Red flags", style = MaterialTheme.typography.titleMedium, color = Color(0xFFB00020), fontWeight = FontWeight.Bold)
                    info.redFlags.forEach { Text("• $it", color = Color(0xFFB00020)) }
                }
                if (info.diet.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Text("Diet", style = MaterialTheme.typography.titleMedium)
                    info.diet.forEach { Text("• $it") }
                }
                if (info.references.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Text("References", style = MaterialTheme.typography.titleMedium)
                    info.references.forEach { Text("• $it") }
                }
            }
        }
    }
}