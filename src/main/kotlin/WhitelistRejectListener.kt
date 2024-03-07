import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class WhitelistRejectListener(val msg: Component) : Listener {
    @EventHandler
    fun onWhitelist(event: PlayerLoginEvent)  {
        if (event.result == PlayerLoginEvent.Result.KICK_WHITELIST)
            {
                event.kickMessage(msg)
            }
    }
}
