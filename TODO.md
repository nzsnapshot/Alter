- ObjectPathAction for doors, wall objects
- Look into the Shop Currency system and decide if it's really how we want to handle it.
- Local npc decode issue where we're sending 27 bytes, but client is only reading 21 bytes. Occurs when you're moving around the viewport edges of an npc.
- Define all messages even for ones that we don't want to handle, this is to avoid byte skipping in the packet decoding net code