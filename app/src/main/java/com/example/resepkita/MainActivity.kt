package com.example.resepkita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resepkita.ui.theme.ResepkitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResepkitaTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

data class FoodItem(
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val cookingSteps: List<String>,
    val imageResId: Int? = null
)

val sampleMenuItems = listOf(
    FoodItem(
        name = "Nasi Goreng Spesial",
        description = "Nasi goreng dengan udang, ayam, telur, dan rempah khusus, dihias dengan irisan timun dan bawang goreng.",
        ingredients = listOf(
            "2 cangkir nasi matang",
            "100g udang",
            "100g dada ayam, potong dadu",
            "2 butir telur",
            "2 siung bawang putih",
            "2 butir bawang merah",
            "2 sdm kecap manis",
            "Garam dan merica secukupnya",
            "Irisan timun",
            "Bawang goreng",
            "2 sdm minyak goreng"
        ),
        cookingSteps = listOf(
            "Panaskan 2 sdm minyak di wajan dengan api sedang.",
            "Tumis bawang putih dan bawang merah hingga harum.",
            "Tambahkan udang dan ayam, masak hingga matang.",
            "Masukkan nasi, kecap manis, dan bumbu, aduk rata.",
            "Tambahkan telur kocok, aduk hingga matang.",
            "Hias dengan timun, bawang goreng, dan sajikan."
        ),
        imageResId = R.drawable.nasigoreng
    ),
    FoodItem(
        name = "Soto Ayam",
        description = "Sup ayam dengan kunyit, serai, mi soun, kol, dan telur rebus.",
        ingredients = listOf(
            "500g ayam",
            "2 batang serai",
            "1 sdt bubuk kunyit",
            "100g mi soun",
            "100g kol, iris tipis",
            "2 butir telur rebus",
            "Bawang goreng",
            "Irisan jeruk nipis",
            "Garam dan merica secukupnya"
        ),
        cookingSteps = listOf(
            "Rebus ayam dengan serai dan kunyit selama 30 menit.",
            "Suwir ayam dan sisihkan kaldu.",
            "Masak mi soun secara terpisah.",
            "Campur kaldu, kol, dan bumbu; didihkan selama 10 menit.",
            "Sajikan dengan mi, ayam suwir, telur, dan hiasan."
        ),
        imageResId = R.drawable.sotoayam
    ),
    FoodItem(
        name = "Rendang Daging",
        description = "Daging sapi dimasak perlahan dalam santan dengan cabai, lengkuas, dan daun jeruk purut.",
        ingredients = listOf(
            "500g daging sapi, potong dadu",
            "400ml santan",
            "5 cabai kering",
            "2 cm lengkuas",
            "3 siung bawang putih",
            "2 daun jeruk purut",
            "1 batang serai",
            "Garam dan gula secukupnya",
            "2 sdm minyak goreng"
        ),
        cookingSteps = listOf(
            "Haluskan bumbu (cabai, lengkuas, bawang putih) menjadi pasta.",
            "Tumis pasta bumbu dengan daun jeruk purut hingga harum.",
            "Tambahkan daging dan santan, masak perlahan selama 3 jam.",
            "Aduk sesekali hingga kuah mengental.",
            "Sajikan dengan nasi putih."
        ),
        imageResId = R.drawable.rendang
    ),
    FoodItem(
        name = "Gado-Gado",
        description = "Sayuran campur dengan saus kacang, tahu, tempe, dan telur rebus, disajikan dengan lontong.",
        ingredients = listOf(
            "100g kol",
            "100g tauge",
            "100g bayam",
            "100g tahu",
            "100g tempe",
            "2 butir telur rebus",
            "3 sdm kacang tanah sangrai",
            "1 siung bawang putih",
            "2 cabai merah",
            "1 sdm gula merah",
            "1 sdm air asam jawa",
            "Garam secukupnya",
            "Lontong"
        ),
        cookingSteps = listOf(
            "Blansir sayuran (kol, tauge, bayam).",
            "Goreng tahu dan tempe hingga kecokelatan.",
            "Haluskan kacang, bawang putih, dan cabai untuk saus.",
            "Campur sayuran, tahu, tempe, dan saus.",
            "Sajikan dengan lontong dan telur rebus."
        ),
        imageResId = R.drawable.gado
    ),
    FoodItem(
        name = "Bakso",
        description = "Sup bakso dengan mi, tahu, dan sayuran dalam kaldu gurih.",
        ingredients = listOf(
            "300g daging sapi giling",
            "2 sdm tepung tapioka",
            "1 butir telur",
            "2 siung bawang putih",
            "1 liter kaldu sapi",
            "Mi",
            "Tahu goreng",
            "Seledri cincang",
            "Bawang goreng",
            "Garam dan merica"
        ),
        cookingSteps = listOf(
            "Haluskan daging, bawang putih, telur, dan tepung tapioka.",
            "Bentuk adonan menjadi bola-bola dan rebus hingga mengapung.",
            "Siapkan kaldu sapi dan didihkan.",
            "Sajikan bakso dengan mi, tahu, kaldu, dan hiasan."
        ),
        imageResId = R.drawable.bakso
    ),
    FoodItem(
        name = "Pempek Palembang",
        description = "Kue ikan goreng disajikan dengan saus cuka pedas.",
        ingredients = listOf(
            "250g fillet ikan (tenggiri atau makarel)",
            "200g tepung tapioka",
            "2 siung bawang putih",
            "Garam dan merica",
            "Cuka",
            "Gula merah",
            "Cabai",
            "Irisan timun"
        ),
        cookingSteps = listOf(
            "Campur pasta ikan dengan bawang putih, garam, dan tepung tapioka.",
            "Bentuk adonan menjadi oval atau lenjer.",
            "Rebus hingga mengapung, lalu goreng hingga kecokelatan.",
            "Sajikan dengan saus cuka pedas dan timun."
        ),
        imageResId = R.drawable.pempek
    ),
    FoodItem(
        name = "Gudeg Jogja",
        description = "Sayur nangka muda manis disajikan dengan nasi, telur, dan ayam.",
        ingredients = listOf(
            "500g nangka muda",
            "2 butir telur rebus",
            "300ml santan",
            "100g gula merah",
            "5 siung bawang putih",
            "4 butir bawang merah",
            "3 daun salam",
            "Garam dan lengkuas"
        ),
        cookingSteps = listOf(
            "Rebus nangka dengan bawang putih, bawang merah, daun salam, dan lengkuas.",
            "Tambahkan santan dan gula merah, masak perlahan selama 3 jam.",
            "Tambahkan telur rebus dan masak lebih lama jika diinginkan.",
            "Sajikan dengan nasi dan ayam."
        ),
        imageResId = R.drawable.gudeg
    ),
    FoodItem(
        name = "Ayam Betutu",
        description = "Ayam panggang khas Bali dengan bumbu kaya rempah.",
        ingredients = listOf(
            "1 ekor ayam utuh",
            "5 siung bawang putih",
            "6 butir bawang merah",
            "2 cm jahe",
            "2 cm lengkuas",
            "5 cabai merah",
            "Kunyit, ketumbar, dan kemiri",
            "Garam dan merica",
            "Daun pisang untuk membungkus"
        ),
        cookingSteps = listOf(
            "Haluskan bumbu menjadi pasta dan oleskan ke ayam.",
            "Bungkus ayam dengan daun pisang dan kukus atau panggang.",
            "Masak hingga ayam empuk dan harum.",
            "Sajikan dengan nasi."
        ),
        imageResId = R.drawable.betutu
    ),
    FoodItem(
        name = "Nasi Uduk",
        description = "Nasi santan disajikan dengan lauk seperti tempe, telur, dan sambal.",
        ingredients = listOf(
            "2 cangkir beras",
            "300ml santan",
            "1 batang serai",
            "2 daun salam",
            "1 butir telur rebus",
            "Tempe goreng",
            "Bawang goreng",
            "Sambal"
        ),
        cookingSteps = listOf(
            "Masak beras dengan santan, serai, dan daun salam.",
            "Sajikan dengan tempe goreng, telur, sambal, dan hiasan."
        ),
        imageResId = R.drawable.uduk
    ),
    FoodItem(
        name = "Tahu Gejrot",
        description = "Tahu goreng dengan saus pedas manis asam.",
        ingredients = listOf(
            "200g tahu goreng",
            "2 siung bawang putih",
            "3 cabai rawit",
            "2 sdm gula merah",
            "2 sdm cuka",
            "Garam",
            "Air"
        ),
        cookingSteps = listOf(
            "Potong tahu goreng menjadi potongan kecil.",
            "Buat saus dengan menghaluskan bawang putih dan cabai, campur dengan gula merah, cuka, garam, dan air.",
            "Tuang saus ke atas tahu dan sajikan."
        ),
        imageResId = R.drawable.gejrot
    ),
    FoodItem(
        name = "Rawon",
        description = "Sup daging hitam dengan kluwek, disajikan dengan nasi dan telur asin.",
        ingredients = listOf(
            "500g daging sapi",
            "3 biji kluwek",
            "5 siung bawang putih",
            "3 butir bawang merah",
            "1 batang serai",
            "2 daun jeruk purut",
            "Garam, gula",
            "Tauge",
            "Telur asin rebus"
        ),
        cookingSteps = listOf(
            "Haluskan bumbu dengan kluwek menjadi pasta.",
            "Tumis pasta bumbu, tambahkan daging dan air.",
            "Masak hingga daging empuk.",
            "Sajikan dengan nasi, tauge, dan telur asin."
        ),
        imageResId = R.drawable.rawon
    ),
    FoodItem(
        name = "Sayur Lodeh",
        description = "Sayur santan yang lembut dan gurih.",
        ingredients = listOf(
            "100g labu siam",
            "100g kacang panjang",
            "50g wortel",
            "300ml santan",
            "2 cabai merah",
            "3 butir bawang merah",
            "2 siung bawang putih",
            "Garam dan gula",
            "Tempe (opsional)"
        ),
        cookingSteps = listOf(
            "Tumis bawang putih, bawang merah, dan cabai.",
            "Tambahkan sayuran dan aduk selama 2 menit.",
            "Tuang santan, masak hingga sayuran empuk.",
            "Bumbui dan sajikan panas."
        ),
        imageResId = R.drawable.lodeh
    ),
    FoodItem(
        name = "Kerak Telor",
        description = "Omelet khas Betawi dengan beras ketan, telur, dan kelapa parut.",
        ingredients = listOf(
            "50g beras ketan",
            "1 butir telur (bebek atau ayam)",
            "2 sdm kelapa parut",
            "Bawang goreng",
            "Garam dan merica"
        ),
        cookingSteps = listOf(
            "Rendam beras ketan, kukus hingga setengah matang.",
            "Di wajan, tambahkan beras, lalu campuran telur dan kelapa.",
            "Masak dengan api kecil hingga bagian bawah renyah.",
            "Sajikan dengan bawang goreng."
        ),
        imageResId = R.drawable.keraktelor
    ),
    FoodItem(
        name = "Ayam Goreng Kalasan",
        description = "Ayam goreng khas Jawa dengan marinasi bumbu kaya.",
        ingredients = listOf(
            "500g ayam",
            "4 siung bawang putih",
            "2 sdt ketumbar",
            "1 sdt garam",
            "1 sdm gula merah",
            "1 cangkir air kelapa",
            "2 daun salam",
            "Minyak untuk menggoreng"
        ),
        cookingSteps = listOf(
            "Rebus ayam dalam air kelapa dengan bumbu hingga air terserap.",
            "Goreng hingga kecokelatan.",
            "Sajikan dengan sambal dan nasi."
        ),
        imageResId = R.drawable.kalasan
    ),
    FoodItem(
        name = "Sate Ayam",
        description = "Sate ayam panggang dengan saus kacang, disajikan dengan nasi dan acar sayuran.",
        ingredients = listOf(
            "500g ayam, potong dadu",
            "2 sdm kecap manis",
            "1 sdt kunyit",
            "Garam dan merica secukupnya",
            "Tusuk sate bambu",
            "3 sdm kacang tanah sangrai",
            "1 sdm kecap manis",
            "1 siung bawang putih",
            "1 sdt bubuk cabai",
            "1 timun",
            "1 wortel",
            "2 sdm cuka",
            "1 sdm gula"
        ),
        cookingSteps = listOf(
            "Marinasi ayam dengan kunyit dan bumbu selama 1 jam.",
            "Tusuk ayam dan panggang hingga matang.",
            "Haluskan kacang, kecap, dan bumbu untuk saus.",
            "Siapkan acar timun dan wortel.",
            "Sajikan sate dengan saus dan nasi."
        ),
        imageResId = R.drawable.sate
    )
)

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(
        modifier = modifier,
        color = Color(0xFFF5F5DC)
    ) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            MenuApp()
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ResepKita",
                color = Color.Black,
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Resep andalan para koki",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(96.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Chef illustration",
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 172.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onContinueClicked,
                modifier = Modifier
                    .width(330.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B2E2E),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun MenuApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu_list") {
        composable("menu_list") {
            MenuListScreen(
                menuItems = sampleMenuItems,
                onItemClick = { foodItem ->
                    navController.navigate("recipe_detail/${foodItem.name}")
                }
            )
        }
        composable("recipe_detail/{foodName}") { backStackEntry ->
            val foodName = backStackEntry.arguments?.getString("foodName")
            val foodItem = sampleMenuItems.find { it.name == foodName }
            foodItem?.let {
                RecipeDetailScreen(foodItem = it, onBackClick = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuListScreen(
    menuItems: List<FoodItem>,
    onItemClick: (FoodItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val filteredItems = menuItems.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ResepKita", style = MaterialTheme.typography.headlineSmall) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF8B2E2E),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { /* Handle search action if needed */ }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )
            // Menu Items
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (filteredItems.isEmpty()) {
                    item {
                        Text(
                            text = "No recipes found",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(filteredItems) { foodItem ->
                        MenuItem(foodItem = foodItem, onClick = { onItemClick(foodItem) })
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItem(foodItem: FoodItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Image Thumbnail
                if (foodItem.imageResId != null) {
                    Image(
                        painter = painterResource(id = foodItem.imageResId),
                        contentDescription = "Image of ${foodItem.name}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = foodItem.name[0].toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            ElevatedButton(
                onClick = onClick,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color(0xFF8B2E2E),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("View Recipe")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(foodItem: FoodItem, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(foodItem.name, style = MaterialTheme.typography.headlineSmall) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF8B2E2E),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Image
            item {
                if (foodItem.imageResId != null) {
                    Image(
                        painter = painterResource(id = foodItem.imageResId),
                        contentDescription = "Image of ${foodItem.name}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Image of ${foodItem.name}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            // Description
            item {
                Column {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = foodItem.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            //Ingredients
            item {
                Column {
                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    foodItem.ingredients.forEachIndexed { index, step ->
                        Text(
                            text = "${index + 1}. $step",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
            }
            // Cooking Steps
            item {
                Column {
                    Text(
                        text = "Cooking Steps",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    foodItem.cookingSteps.forEachIndexed { index, step ->
                        Text(
                            text = "${index + 1}. $step",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ResepkitaTheme {
        MenuApp()
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ResepkitaTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    ResepkitaTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailPreview() {
    ResepkitaTheme {
        RecipeDetailScreen(
            foodItem = sampleMenuItems[0],
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuListPreview() {
    ResepkitaTheme {
        MenuListScreen(
            menuItems = sampleMenuItems,
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    ResepkitaTheme {
        MenuItem(
            foodItem = sampleMenuItems[0],
            onClick = {}
        )
    }
}

