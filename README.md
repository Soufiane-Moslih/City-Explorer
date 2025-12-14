# City Explorer - Application Android

Une application Android native permettant d'explorer les villes marocaines avec différentes vues, une carte interactive, des galeries d'images et bien plus encore.

## 📱 Fonctionnalités

- **Vues multiples** : Liste, Grille et Spinner pour parcourir les villes
- **Carte interactive** : Carte Google Maps avec marqueurs personnalisés pour chaque ville
- **Localisation GPS** : Affichage de votre position actuelle sur la carte
- **Types de cartes** : Basculer entre Normal, Satellite, Terrain et Hybride
- **Galeries d'images** : Sliders d'images pour plusieurs villes (Casablanca, Marrakech, Tanger, Ifrane, Agadir, Rabat)
- **Mode sombre/clair** : Support complet du thème sombre pour toute l'application
- **Multilingue** : Support Français/Anglais avec changement de langue dynamique
- **Galerie photos** : Vue d'ensemble de toutes les photos des villes
- **Détails des villes** : Informations détaillées avec descriptions et images

## 🛠️ Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- **Android Studio** (version récente recommandée)
- **JDK 11** ou supérieur
- **Android SDK** avec :
  - API Level 21 (Android 5.0) minimum
  - API Level 36 (Android 15) cible
- **Clé API Google Maps** : Obtenez votre clé sur [Google Cloud Console](https://console.cloud.google.com/)

## 📦 Installation

### 1. Cloner le dépôt

```bash
git clone https://github.com/Soufiane-Moslih/City-Explorer.git
cd City-Explorer
```

### 2. Ouvrir dans Android Studio

1. Lancez Android Studio
2. Sélectionnez **File > Open**
3. Naviguez vers le dossier du projet et ouvrez-le
4. Android Studio synchronisera automatiquement les dépendances Gradle

### 3. Configurer la clé API Google Maps

**⚠️ Important** : Remplacez la clé API Google Maps par la vôtre.

1. Obtenez votre clé API sur [Google Cloud Console](https://console.cloud.google.com/)
   - Créez un projet ou sélectionnez un projet existant
   - Activez l'API "Maps SDK for Android"
   - Créez une clé API et restreignez-la à "Android apps"

2. Modifiez le fichier `app/src/main/AndroidManifest.xml` :

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="VOTRE_CLE_API_ICI" />
```

Remplacez `VOTRE_CLE_API_ICI` par votre clé API Google Maps.

### 4. Synchroniser le projet

1. Android Studio devrait synchroniser automatiquement
2. Si ce n'est pas le cas, cliquez sur **File > Sync Project with Gradle Files**

## 🚀 Exécution

### Sur un émulateur Android

1. Créez un émulateur Android dans Android Studio :
   - **Tools > Device Manager**
   - Cliquez sur **Create Device**
   - Sélectionnez un appareil et une image système (API 21+)

2. Lancez l'application :
   - Cliquez sur le bouton **Run** (▶️) dans la barre d'outils
   - Ou utilisez le raccourci `Shift + F10`

### Sur un appareil physique

1. Activez le **Mode développeur** sur votre appareil Android :
   - Allez dans **Paramètres > À propos du téléphone**
   - Appuyez 7 fois sur **Numéro de build**

2. Activez le **Débogage USB** :
   - **Paramètres > Options pour les développeurs > Débogage USB**

3. Connectez votre appareil via USB

4. Autorisez le débogage USB lorsque demandé

5. Lancez l'application depuis Android Studio

## 📁 Structure du projet

```
App_ListGridSpinnerr/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/app_listgridspinnerr/
│   │   │   │   ├── MainActivity.java          # Activité principale
│   │   │   │   ├── MenuActivity.java          # Menu de navigation
│   │   │   │   ├── ListActivity.java          # Vue liste des villes
│   │   │   │   ├── GridActivity.java          # Vue grille des villes
│   │   │   │   ├── SpinnerActivity.java       # Vue spinner des villes
│   │   │   │   ├── MapsActivity.java          # Carte interactive
│   │   │   │   ├── CityDetailActivity.java    # Détails d'une ville
│   │   │   │   ├── GalleryActivity.java       # Galerie photos
│   │   │   │   ├── Imagelist_Act.java         # Liste d'images
│   │   │   │   ├── ImageSliderAdapter.java    # Adaptateur pour sliders
│   │   │   │   ├── SettingsHelper.java        # Gestion des paramètres
│   │   │   │   └── ...
│   │   │   ├── res/
│   │   │   │   ├── layout/                    # Fichiers de mise en page XML
│   │   │   │   ├── drawable/                  # Images et ressources graphiques
│   │   │   │   ├── values/                    # Ressources (strings, colors, etc.)
│   │   │   │   │   ├── strings.xml            # Chaînes en français
│   │   │   │   │   └── strings-en.xml         # Chaînes en anglais
│   │   │   │   └── raw/                       # Fichiers multimédias bruts
│   │   │   └── AndroidManifest.xml            # Manifeste de l'application
│   │   └── build.gradle.kts                   # Configuration Gradle du module
│   └── build.gradle.kts
├── build.gradle.kts                            # Configuration Gradle du projet
└── README.md
```

## 🎯 Utilisation

### Navigation principale

- **Accueil** : Écran principal avec accès rapide aux différentes fonctionnalités
- **Menu** : Menu latéral avec toutes les options de navigation
- **Paramètres** : Accessibles via le menu (icône ⚙️) pour changer le thème et la langue

### Vues des villes

- **Liste** : Vue en liste verticale des villes
- **Grille** : Vue en grille des villes
- **Spinner** : Sélection via un menu déroulant

### Carte interactive

- **Marqueurs** : Cliquez sur un marqueur pour voir les détails de la ville
- **GPS** : Bouton bleu en bas à gauche pour afficher votre position actuelle
- **Types de cartes** : Bouton au-dessus du GPS pour changer le type de carte
- **Sliders d'images** : Certaines villes affichent plusieurs images dans un slider

### Galerie

- Accédez à la galerie depuis le menu pour voir toutes les photos des villes

## 🔧 Technologies utilisées

- **Langage** : Java 11
- **Framework** : Android SDK
- **Bibliothèques principales** :
  - `androidx.appcompat` - Support des fonctionnalités Android modernes
  - `com.google.android.material` - Composants Material Design
  - `com.google.android.gms:play-services-maps` - Google Maps SDK
  - `com.google.android.gms:play-services-location` - Services de localisation
  - `androidx.viewpager2` - Sliders d'images
  - `androidx.constraintlayout` - Mise en page flexible

## 📝 Permissions

L'application requiert les permissions suivantes :

- `INTERNET` - Pour charger les cartes et les ressources en ligne
- `ACCESS_NETWORK_STATE` - Pour vérifier la connectivité
- `ACCESS_FINE_LOCATION` - Pour obtenir la position GPS précise
- `ACCESS_COARSE_LOCATION` - Pour obtenir la position approximative

Ces permissions sont déclarées dans `AndroidManifest.xml` et demandées à l'exécution pour la localisation.

## 🎨 Personnalisation

### Ajouter une nouvelle ville

1. Ajoutez l'image de la ville dans `app/src/main/res/drawable/`
2. Ajoutez la description dans `app/src/main/res/values/strings.xml` :
   ```xml
   <string name="nom_ville_description">Description de la ville</string>
   ```
3. Ajoutez un marqueur dans `MapsActivity.java` avec les coordonnées GPS

### Ajouter des images à un slider

Modifiez la méthode `setupImageSlider` dans `MapsActivity.java` ou `CityDetailActivity.java` :

```java
List<Integer> images = Arrays.asList(
    R.drawable.ville1,
    R.drawable.ville2,
    R.drawable.ville3
);
```

## 🐛 Dépannage

### L'application ne compile pas

- Vérifiez que toutes les dépendances sont synchronisées
- Assurez-vous d'utiliser JDK 11 ou supérieur
- Nettoyez le projet : **Build > Clean Project**

### La carte ne s'affiche pas

- Vérifiez que votre clé API Google Maps est correctement configurée
- Assurez-vous que l'API "Maps SDK for Android" est activée dans Google Cloud Console
- Vérifiez que votre appareil/émulateur a une connexion Internet

### Les images ne s'affichent pas

- Vérifiez que les fichiers images existent dans `app/src/main/res/drawable/`
- Vérifiez les noms des ressources dans le code
- Redémarrez l'application après avoir ajouté de nouvelles images

## 🤝 Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. Forkez le projet
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Pushez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👤 Auteur

**Soufiane Moslih**

- GitHub: [@Soufiane-Moslih](https://github.com/Soufiane-Moslih)

## 📞 Support

Pour toute question ou problème, ouvrez une [issue](https://github.com/Soufiane-Moslih/City-Explorer/issues) sur GitHub.

---

**Note** : N'oubliez pas de remplacer la clé API Google Maps par la vôtre avant de publier l'application !

