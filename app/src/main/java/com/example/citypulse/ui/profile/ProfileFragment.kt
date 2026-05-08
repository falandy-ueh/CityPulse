package com.example.citypulse.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.citypulse.R
import com.example.citypulse.databinding.FragmentProfileBinding
import com.example.citypulse.databinding.ItemSettingsRowBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Fragment de profil gérant les statistiques de l'utilisateur et les paramètres de l'application.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSettingsItems()
        setupListeners()
    }

    private fun setupSettingsItems() {
        // Mode Sombre
        setupRow(binding.itemDarkMode, "Thème sombre", android.R.drawable.ic_menu_daynight, isSwitch = true) { isChecked ->
            val mode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)
        }

        // Notifications
        setupRow(binding.itemNotifications, "Notifications", android.R.drawable.ic_popup_reminder, isSwitch = true) { isChecked ->
            // Logique de notification à implémenter
        }

        // Localisation
        setupRow(binding.itemLocation, "Localisation arrière-plan", android.R.drawable.ic_menu_mylocation, isSwitch = true) { isChecked ->
            if (isChecked) {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", requireContext().packageName, null)
                }
                startActivity(intent)
            }
        }

        // Langue
        setupRow(binding.itemLanguage, "Langue", android.R.drawable.ic_menu_mapmode, value = "Français") {
            showLanguageDialog()
        }

        // À propos
        setupRow(binding.itemAbout, "À propos", android.R.drawable.ic_menu_info_details) {
            showAboutDialog()
        }
    }

    private fun setupRow(
        rowBinding: ItemSettingsRowBinding,
        title: String,
        iconRes: Int,
        isSwitch: Boolean = false,
        value: String? = null,
        onAction: (Boolean) -> Unit = {}
    ) {
        rowBinding.apply {
            tvSettingTitle.text = title
            ivSettingIcon.setImageResource(iconRes)

            if (isSwitch) {
                switchSetting.visibility = View.VISIBLE
                ivChevron.visibility = View.GONE
                switchSetting.setOnCheckedChangeListener { _, isChecked -> onAction(isChecked) }
                // Initialiser l'état du switch (ex: pour le thème sombre)
                if (title == "Thème sombre") {
                    switchSetting.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
                }
            } else {
                switchSetting.visibility = View.GONE
                ivChevron.visibility = View.VISIBLE
                if (value != null) {
                    tvSettingValue.text = value
                    tvSettingValue.visibility = View.VISIBLE
                }
                root.setOnClickListener { onAction(true) }
            }
        }
    }

    private fun setupListeners() {
        binding.btnResetApp.setOnClickListener {
            showResetConfirmation()
        }
    }

    private fun showLanguageDialog() {
        val languages = arrayOf("Français", "English")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Sélectionner la langue")
            .setItems(languages) { _, which ->
                binding.itemLanguage.tvSettingValue.text = languages[which]
            }
            .show()
    }

    private fun showAboutDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("À propos de CityPulse")
            .setMessage("Version 1.0.0\n\nDéveloppé avec ❤️ par l'équipe CityPulse :\n- Ruben Guerrier\n- [Votre Nom/Équipe]\n\nApplication native Kotlin - Material 3")
            .setPositiveButton("Fermer", null)
            .show()
    }

    private fun showResetConfirmation() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Réinitialiser l'application ?")
            .setMessage("Cette action supprimera tous vos favoris et vos notes personnelles. Elle est irréversible.")
            .setNegativeButton("Annuler", null)
            .setPositiveButton("Réinitialiser") { _, _ ->
                // Logique de reset (SharedPreferences + Database)
                val sharedPref = requireActivity().getSharedPreferences("citypulse_prefs", Context.MODE_PRIVATE)
                sharedPref.edit().clear().apply()
                // Redémarrage ou retour à l'onboarding
                requireActivity().finish()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
