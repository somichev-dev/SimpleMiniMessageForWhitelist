import org.bukkit.block.CommandBlock
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class CommandManager(private val plugin: SMMW) : CommandExecutor, TabCompleter {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>,
    ): Boolean {
        if (sender is CommandBlock) return true
        if (args.size !in 1..2) {
            sender.sendMessage(
                "/smmw view - see current whitelist message" +
                    "\n/smmw set [message] - set new whitelist message",
            )
            return true
        }
        val op = args[0]
        if (op == "view") {
            sender.sendMessage(plugin.whitelistMessage)
        } else if (op == "set" && args.size == 2) {
            plugin.config.set("message", args[1])
            return true
        }
        sender.sendMessage(
            "/smmw view - see current whitelist message" +
                "\n/smmw set [message] - set new whitelist message",
        )
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>,
    ): MutableList<String>? {
        if (args.size == 1) {
            return mutableListOf("view", "set")
        }
        return null
    }
}
