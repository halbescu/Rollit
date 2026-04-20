# Rollit

Rollit is relatively configurable plugin for rolling DnD-esque dices!

## Commands

`/roll [number_of_dice]d[number_of_sides]` -> Rolls a dice. (eg. /roll 2d6 would roll two 6 sided die)</br>
`/reload` -> Reloads the configuration


## Permissions

`rollit.roll` -> Allows user to roll the dice</br>
`rollit.reload` -> Allows reloading the plugin config


---

## Default Configuration File

```yaml
# Maximum amount of dices a player can roll 
max_dice: 100

# Maximum amount of sides a player can give to the dice
max_sides: 100

# Radius (in blocks) in which nearby players will get a roll message with the 'l' option
local_radius: 200
```