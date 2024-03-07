import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SMMW : JavaPlugin() {
    lateinit var whitelistMessage: Component

    override fun onEnable() {
        this.getCommand("smmw")?.setExecutor(CommandManager(this))
        this.saveDefaultConfig()
        val config = this.config
        val rawMsg = config.get("message") ?: throw IllegalArgumentException("No message in config!")
        whitelistMessage = MiniMessage.miniMessage().deserialize(rawMsg as String)

        Bukkit.getPluginManager().registerEvents(WhitelistRejectListener(this.whitelistMessage), this)
    }
}
