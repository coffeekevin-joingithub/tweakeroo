package fi.dy.masa.tweakeroo.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.config.ConfigType;
import fi.dy.masa.malilib.config.IConfigBoolean;
import fi.dy.masa.malilib.config.IConfigNotifiable;
import fi.dy.masa.malilib.config.IHotkeyTogglable;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyCallbackToggleBooleanConfigWithMessage;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.interfaces.IValueChangeCallback;
import fi.dy.masa.malilib.util.StringUtils;
import fi.dy.masa.tweakeroo.Tweakeroo;

public enum FeatureToggle implements IHotkeyTogglable, IConfigNotifiable<IConfigBoolean>
{
    CARPET_ACCURATE_PLACEMENT_PROTOCOL ("carpetAccuratePlacementProtocol",  false, "",    "如果啟用本功能，則Flexible Block Placemen和\nAccurate Block Placement將會使用最新的\n通過CarpetMod實現的協議", "Carpet protocol Accurate Placement"),
    FAST_PLACEMENT_REMEMBER_ALWAYS  ("fastPlacementRememberOrientation",    true, "",     "啟用該選項後，快速建造模式將始終記住您放置的第一個方塊的方向，\n否則將僅在啟用並激活靈活放置功能的情況下記住方塊方向", "Fast Placement Remember Orientation"),
    REMEMBER_FLEXIBLE               ("rememberFlexibleFromClick",           true, "",     "啟用該選項後，只要按住使用鍵，就會從第一個放置的方塊中記住靈活方塊的放置狀態。", "Remember Flexible Orientation From First Click"),
    TWEAK_ACCURATE_BLOCK_PLACEMENT  ("tweakAccurateBlockPlacement",         false, "",    "啟用更簡單的「靈活放置」，類似於Carpet mod，因此基本可以使所單擊的方塊朝向內或向外。"),
    TWEAK_AFTER_CLICKER             ("tweakAfterClicker",                   false, "",    KeybindSettings.INGAME_BOTH, "啟用自動調整功能，該功能會自動在剛放置的方塊上單擊滑鼠右鍵。\n對於設定中繼器延遲來說很有用。\n要快速調整單擊次數，請在按住繫結按鍵的同時滾動滾輪"),
    TWEAK_AIM_LOCK                  ("tweakAimLock",                        false, "",    "啟用瞄準鎖定，將偏航和俯仰旋轉鎖定為目前值。這與捕捉鎖定不同，後者將鎖定鎖定為捕捉值，從而可以將鎖定自由鎖定為目前值"),
    TWEAK_ANGEL_BLOCK               ("tweakAngelBlock創造下在空中放置方塊",                     false, "",    "啟用「天使方塊」選項，從而允許在半空中放置方塊（僅限創造模式）"),
    TWEAK_BLOCK_REACH_OVERRIDE      ("tweakBlockReachOverride",             false, "",    "使用在 通用 -> blockReachDistance 中設定的距離覆蓋預設的玩家可觸碰到的方塊距離"),
    TWEAK_BREAKING_GRID             ("tweakBreakingGrid",                   false, "",    KeybindSettings.INGAME_BOTH, "啟用後，您只能在可配置的間隔中破壞方塊。\n要快速調整間隔，請在按住繫結按鍵的同時滾動滾輪"),
    TWEAK_BREAKING_RESTRICTION      ("tweakBreakingRestriction",            false, "",    "啟用「突破限制」模式（平面，圖層，面，列，直線，對角線）。\n簡單來說按住左鍵不能連續破壞方塊，需要多次點選"),
    TWEAK_CHAT_BACKGROUND_COLOR     ("tweakChatBackgroundColor修改聊天訊息顏色",            false, "",    "使用在 通用 ->chat BackgroundColor 中設定的顏色覆蓋預設的聊天背景顏色"),
    TWEAK_CHAT_PERSISTENT_TEXT      ("tweakChatPersistentText儲存輸入文字",             false, "",    "儲存聊天框中的文字，並在再次打開聊天框時將其還原"),
    TWEAK_CHAT_TIMESTAMP            ("tweakChatTimestamp訊息時間點標記",                  false, "",    "在聊天訊息上新增時間點標記"),
    TWEAK_COMMAND_BLOCK_EXTRA_FIELDS("tweakCommandBlockExtraFields",        false, "",    "向命令方塊的 GUI 新增額外的欄位，用於設定命令塊的名稱並檢視統計資訊結果"),
    TWEAK_CUSTOM_FLAT_PRESETS       ("tweakCustomFlatPresets",              false, "",    "允許將自定義超平坦世界預設新增到列表中。預設需要在 列表 -> flatWorldPresets 中定義"),
    TWEAK_ELYTRA_CAMERA             ("tweakElytraCamera",                   false, "",    "允許在按住 \"elytraCamera\" 啟用鍵的同時鎖定玩家的真實旋轉，\n然後控制元件將僅影響單獨的'視角旋轉'。\n推薦僅在良好而筆直地飛行時向下/環顧四周"),
    TWEAK_SHULKERBOX_STACKING       ("tweakEmptyShulkerBoxesStack界伏盒堆疊",         false, true, "",    "啟用該選項後，最多可堆疊64個空的界伏盒。\n注意：它們將堆疊在一起！在伺服器上，這將導致故障，除非伺服器具有與之相同的 mod。\n在單人遊戲中，這會更改基於界伏盒的系統行為"),
    TWEAK_SHULKERBOX_STACK_GROUND   ("tweakEmptyShulkerBoxesStackOnGround界伏盒掉落物堆疊", false, true, "",    "當作為地面上的物品時，最多可堆疊64個空的界伏盒"),
    TWEAK_EXPLOSION_REDUCED_PARTICLES ("tweakExplosionReducedParticles關閉爆炸粒子效果",    false, "",    "啟用該選項後，所有爆炸粒子都將使用 EXPLOSION_NORMAL 粒子，\n而不是 EXPLOSION LARGE 或 EXPLOSION HUGE 粒子"),
    TWEAK_F3_CURSOR                 ("tweakF3Cursor",                       false, "",    "啟用該選項後，將始終顯示與F3螢幕中樣式相同的三維游標"),
    TWEAK_FAKE_SNEAKING             ("tweakFakeSneaking虛假潛行",                   false, "",    "啟用虛假潛行，可以防止您從邊緣跌落，且不降低移動速度"),
    TWEAK_FAST_BLOCK_PLACEMENT      ("tweakFastBlockPlacement快速放置",             false, "",    "將游標移到新方塊上時，快速地放置方塊"),
    TWEAK_FAST_LEFT_CLICK           ("tweakFastLeftClick快速左鍵單擊",                  false, "",    "在按住攻擊按鈕的時候自動快速單擊左鍵。\n在 通用 -> fastLeftClickCount 中可以設定每個 tick 的點選次數"),
    TWEAK_FAST_RIGHT_CLICK          ("tweakFastRightClick快速右鍵單擊",                 false, "",    "在按住攻擊按鈕的時候自動快速單擊右鍵。\n在 通用 -> fastRightClickCount 中可以設定每個 tick 的點選次數"),
    TWEAK_FILL_CLONE_LIMIT          ("tweakFillCloneLimit填充或複製方塊上限調整",                 false, true, "",    "允許在單人遊戲中自定義/fill和/clone命令的方塊上限,\n可以在 通用 -> fillCloneLimit中調整"),
    TWEAK_FLY_SPEED                 ("tweakFlySpeed飛行速度",                       false, "",    KeybindSettings.INGAME_BOTH, "允許在創造或旁觀者模式自定義飛行速度併爲其使用一些預設"),
    TWEAK_FLEXIBLE_BLOCK_PLACEMENT  ("tweakFlexibleBlockPlacement",         false, "",    "按住熱鍵來以不同狀態放置方塊"),
    TWEAK_FREE_CAMERA               ("tweakFreeCamera靈魂出竅",                     false, "",    "開啟自由視角模式(觀察者模式),但玩家將保留在首次啟用該模式的位置,啟用之後再啟用一次該模式將關閉該模式"),
    TWEAK_FREE_CAMERA_MOTION        ("tweakFreeCameraMotion移動輸入不影響肉體",               false, "",    "啟用後，在自由視角模式模式下的WASD將不會輸入給玩家本體\n您可以通過禁用此選項來控制\n在 自由視角 模式下的 玩家位置\n通常你只是想在 自由視角 模式下控制自由視角的位置,\n你只需要設置FreeCamera和FreeCameraMotion這兩個熱鍵就好"),
    TWEAK_GAMMA_OVERRIDE            ("tweakGammaOverride無限夜視",                  false, "",    "遊戲中的亮度顯示將被 通用 設置的值覆蓋\n可以在 通用 中的更改Gamma值"),
    TWEAK_HAND_RESTOCK              ("tweakHandRestock自動補貨",                    false, "",    "啟用後 主手 或是 副手 使用的物品將耗盡時\n將會從 背包 自動補充到快耗盡的物品中"),
    TWEAK_HOLD_ATTACK               ("tweakHoldAttack長按攻擊",                     false, "",    "模擬按住左鍵"),
    TWEAK_HOLD_USE                  ("tweakHoldUse長按使用",                        false, "",    "模擬按住右鍵"),
    TWEAK_HOTBAR_SCROLL             ("tweakHotbarScroll",                   false, "",    "啟用物品欄滾動選擇"),
    TWEAK_HOTBAR_SLOT_CYCLE         ("tweakHotbarSlotCycle按序切換物品欄",                false, "",    KeybindSettings.INGAME_BOTH, "啟用後 使用物品將從第一格開始循環\n直到設定的最大編號，再回到第一格\n要快速的調整編號\n按住該 熱鍵 並配合 滑鼠滾輪轉動 來調整"),
    TWEAK_HOTBAR_SLOT_RANDOMIZER    ("tweakHotbarSlotRandomizer隨機切換物品欄",           false, "",    KeybindSettings.INGAME_BOTH, "啟用後 使用物品將 隨機選取，物品欄中的物品\n直到設定的物品欄位置\n要快速的調整物品欄位置\n按住該 熱鍵 並配合 滑鼠滾輪轉動 來調整"),
    TWEAK_HOTBAR_SWAP               ("tweakHotbarSwap",                     false, "",    "通過熱鍵啟用物品欄滾動選擇功能"),
    TWEAK_INVENTORY_PREVIEW         ("tweakInventoryPreview容器預覽",               false, true, "",    "啟用後 將游標移到具有清單\n的實體上同時按住，該選項的熱鍵\n將會啟用 物品預覽"),
    TWEAK_ITEM_UNSTACKING_PROTECTION("tweakItemUnstackingProtection",       false, "",    "防止在舀岩漿時因為揹包滿了而把岩漿桶扔進岩漿,\n可以在 列表 -> unstackingItems中自定義適用的物品(水瓶之類的)"),
    TWEAK_LAVA_VISIBILITY           ("tweakLavaVisibility岩漿夜視",                 false, "",    "岩 漿 夜 視\n(戴著水下呼吸的帽子進岩漿可以看得更清楚)"),
    TWEAK_MAP_PREVIEW               ("tweakMapPreview地圖預覽",                     false, "",    "如果啟用，則在按住shift指向地圖將呈現地圖預覽"),
    TWEAK_MOVEMENT_KEYS             ("tweakMovementKeysLast",               false, "",    "啟用後按下相反的移動鍵不會停下來，而是以最後按的方向鍵的方向進行移動"),
    TWEAK_PERIODIC_ATTACK           ("tweakPeriodicAttack定時攻擊",                 false, "",    "啟用定期攻擊（單擊滑鼠左鍵）\n在 通用 -> periodicAttackInterval 中設定"),
    TWEAK_PERIODIC_USE              ("tweakPeriodicUse定時使用",                    false, "",    "啟用定期使用（單擊滑鼠右鍵）\n在 通用 -> periodicUseInterval 中設定"),
    TWEAK_PERMANENT_SNEAK           ("tweakPermanentSneak一直潛行",                 false, "",    "如果啟用，玩家將一直潛行"),
    TWEAK_PERMANENT_SPRINT          ("tweakPermanentSprint一直衝刺",                false, "",    "如果啟用，玩家將在向前移動時始終處於衝刺狀態"),
    TWEAK_PLACEMENT_GRID            ("tweakPlacementGrid",                  false, "",    KeybindSettings.INGAME_BOTH, "啟用後，您只能以可配置的間隔將方塊放置在網格模式中。\n要快速調整值，請在熱鍵的同時滾動滾輪."),
    TWEAK_PLACEMENT_LIMIT           ("tweakPlacementLimit",                 false, "",    KeybindSettings.INGAME_BOTH, "啟用後，每次使用/右鍵單擊只能放置一定數量的方塊。\n要快速調整值，請在按住調整切換鍵繫結的同時滾動。"),
    TWEAK_PLACEMENT_RESTRICTION     ("tweakPlacementRestriction",           false, "",    "啟用展示位置限制模式\n  (平面，圖層，面，列，直線，對角線)"),
    TWEAK_PLACEMENT_REST_FIRST      ("tweakPlacementRestrictionFirst",      false, "",    "限制方塊放置，以便您只將方塊放置在首次單擊的相同型別方塊上"),
    TWEAK_PLACEMENT_REST_HAND       ("tweakPlacementRestrictionHand",       false, "",    "限制方塊放置，以便您只將手握的相同方塊放置在相同型別方塊上"),
    TWEAK_PLAYER_INVENTORY_PEEK     ("tweakPlayerInventoryPeek",            false, "",    "在按住配置的熱鍵時，開啟玩家揹包的瀏覽/預覽。"),
    TWEAK_POTION_WARNING            ("tweakPotionWarning藥水耗盡警告",                  false, "",    "當非環境藥水效果即將用盡時\n將警告訊息顯示在快捷快捷欄上方"),
    TWEAK_PRINT_DEATH_COORDINATES   ("tweakPrintDeathCoordinates發送死亡座標",          false, "",    "玩家死亡後，發送座標至聊天欄。\n此功能最初來自nessie的有用的mod。"),
    TWEAK_PICK_BEFORE_PLACE         ("tweakPickBeforePlace",                false, "",    "如果啟用，則在每個方塊放置之前，將切換到要放置的同一個方塊"),
    TWEAK_PLAYER_LIST_ALWAYS_ON     ("tweakPlayerListAlwaysVisible",        false, "",    "如果啟用，則在不按住熱鍵鍵的情況下呈現玩家列表（預設情況下為TAB列表）"),
    TWEAK_REMOVE_OWN_POTION_EFFECTS ("tweakRemoveOwnPotionEffects移除藥水效果粒子",         false, "",    "在第一人稱模式下從玩家身上移除藥水效果粒子"),
    TWEAK_RENDER_INVISIBLE_ENTITIES ("tweakRenderInvisibleEntities渲染不可見實體",        false, "",    "啟用後，將渲染不可見實體，就像它們處於旁觀者模式一樣"),
    TWEAK_RENDER_LIMIT_ENTITIES     ("tweakRenderLimitEntities限制每幀渲染實體數",            false, "",    "允許限制每幀要渲染的某些特定型別的實體的數量。 \n目前不支援 經驗球 和 物品 實體\n請參閱 通用 以瞭解限制。"),
    TWEAK_REPAIR_MODE               ("tweakRepairMode物品輪替修復模式",                     false, "",    "將損壞的且具有經驗修補的物品交換到玩家的手上\n允許在站在經驗農場時輪流自動修復損壞工具"),
    TWEAK_SHULKERBOX_DISPLAY        ("tweakShulkerBoxDisplay界伏盒物品預覽",              false, "",    "按住shift並且將游標移動到物品上，可以預覽界伏盒內物品(以及所有帶有內容物的方塊)"),
    TWEAK_SIGN_COPY                 ("tweakSignCopy告示牌文字複製",                       false, "",    "啟用後，放置的告示牌將使用先前放置的告示牌中的文字。\n可以與 禁止告示牌GUI打開結合使用，以在製作第一個標記後\n啟用該功能來快速放置告示牌的副本。"),
    TWEAK_SNAP_AIM                  ("tweakSnapAim鎖定為 設定的視角",                        false, "",    KeybindSettings.INGAME_BOTH, "啟用了本功能，使玩家面對預先設定的精確偏航旋轉"),
    TWEAK_SNAP_AIM_LOCK             ("tweakSnapAimLock鎖定視角",                    false, "",    "啟用對齊鎖定，將偏航和/或俯仰旋轉\n鎖定為目前對齊值"),
    TWEAK_SPECTATOR_TELEPORT        ("tweakSpectatorTeleport旁觀可以傳旁觀",              false, "",    "允許旁觀模式玩家傳送到其他旁觀模式玩家。\n最初來自nessie有用的mod"),
    TWEAK_STRUCTURE_BLOCK_LIMIT     ("tweakStructureBlockLimit重寫結構方塊上限",            false, true, "",    "允許重寫結構方塊限制。\n在 通用 -> structureBlockMaxSize 中修改上限"),
    TWEAK_SWAP_ALMOST_BROKEN_TOOLS  ("tweakSwapAlmostBrokenTools防止工具完全損壞",          false, "",    "如果啟用，則手中即將破損的任何可損壞物品都將從揹包拿取新的並取代手中的物品"),
    TWEAK_TAB_COMPLETE_COORDINATE   ("tweakTabCompleteCoordinate",          false, "",    "如果啟用此選項，則在準星不對準方塊的情況下\n使用TAB補全座標將使用玩家的位置\n而不是新增~字元."),
    TWEAK_TOOL_SWITCH               ("tweakToolSwitch自動切換對應方塊有效工具",                     false, "",    "能夠自動切換到針對目標方塊的有效工具"),
    TWEAK_Y_MIRROR                  ("tweakYMirror",                        false, "",    "將目標y位置映象到塊邊界內。\n這基本上是用於將半磚或樓梯放置在與法線相反的頂部/底部狀態"),
    TWEAK_ZOOM                      ("tweakZoom畫面放大",                           false, "",    KeybindSettings.INGAME_BOTH, "啟用使用縮放熱鍵來放大畫面(可能)");

    private final String name;
    private final String comment;
    private final String prettyName;
    private final IKeybind keybind;
    private final boolean defaultValueBoolean;
    private final boolean singlePlayer;
    private boolean valueBoolean;
    private IValueChangeCallback<IConfigBoolean> callback;

    private FeatureToggle(String name, boolean defaultValue, String defaultHotkey, String comment)
    {
        this(name, defaultValue, false, defaultHotkey, KeybindSettings.DEFAULT, comment);
    }

    private FeatureToggle(String name, boolean defaultValue, boolean singlePlayer, String defaultHotkey, String comment)
    {
        this(name, defaultValue, singlePlayer, defaultHotkey, KeybindSettings.DEFAULT, comment);
    }

    private FeatureToggle(String name, boolean defaultValue, String defaultHotkey, KeybindSettings settings, String comment)
    {
        this(name, defaultValue, false, defaultHotkey, settings, comment);
    }

    private FeatureToggle(String name, boolean defaultValue, boolean singlePlayer, String defaultHotkey, KeybindSettings settings, String comment)
    {
        this(name, defaultValue, singlePlayer, defaultHotkey, settings, comment, StringUtils.splitCamelCase(name.substring(5)));
    }

    private FeatureToggle(String name, boolean defaultValue, String defaultHotkey, String comment, String prettyName)
    {
        this(name, defaultValue, false, defaultHotkey, comment, prettyName);
    }

    private FeatureToggle(String name, boolean defaultValue, boolean singlePlayer, String defaultHotkey, String comment, String prettyName)
    {
        this(name, defaultValue, singlePlayer, defaultHotkey, KeybindSettings.DEFAULT, comment, prettyName);
    }

    private FeatureToggle(String name, boolean defaultValue, boolean singlePlayer, String defaultHotkey, KeybindSettings settings, String comment, String prettyName)
    {
        this.name = name;
        this.valueBoolean = defaultValue;
        this.defaultValueBoolean = defaultValue;
        this.singlePlayer = singlePlayer;
        this.comment = comment;
        this.prettyName = prettyName;
        this.keybind = KeybindMulti.fromStorageString(defaultHotkey, settings);
        this.keybind.setCallback(new KeyCallbackToggleBooleanConfigWithMessage(this));
    }

    @Override
    public ConfigType getType()
    {
        return ConfigType.HOTKEY;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getConfigGuiDisplayName()
    {
        if (this.singlePlayer)
        {
            return GuiBase.TXT_GOLD + this.getName() + GuiBase.TXT_RST;
        }

        return this.getName();
    }

    @Override
    public String getPrettyName()
    {
        return this.prettyName;
    }

    @Override
    public String getStringValue()
    {
        return String.valueOf(this.valueBoolean);
    }

    @Override
    public String getDefaultStringValue()
    {
        return String.valueOf(this.defaultValueBoolean);
    }

    @Override
    public void setValueFromString(String value)
    {
    }

    @Override
    public void onValueChanged()
    {
        if (this.callback != null)
        {
            this.callback.onValueChanged(this);
        }
    }

    @Override
    public void setValueChangeCallback(IValueChangeCallback<IConfigBoolean> callback)
    {
        this.callback = callback;
    }

    @Override
    public String getComment()
    {
        if (this.comment == null)
        {
            return "";
        }

        if (this.singlePlayer)
        {
            return this.comment + "\n" + StringUtils.translate("tweakeroo.label.config_comment.single_player_only");
        }
        else
        {
            return this.comment;
        }
    }

    @Override
    public IKeybind getKeybind()
    {
        return this.keybind;
    }

    @Override
    public boolean getBooleanValue()
    {
        return this.valueBoolean;
    }

    @Override
    public boolean getDefaultBooleanValue()
    {
        return this.defaultValueBoolean;
    }

    @Override
    public void setBooleanValue(boolean value)
    {
        boolean oldValue = this.valueBoolean;
        this.valueBoolean = value;

        if (oldValue != this.valueBoolean)
        {
            this.onValueChanged();
        }
    }

    @Override
    public boolean isModified()
    {
        return this.valueBoolean != this.defaultValueBoolean;
    }

    @Override
    public boolean isModified(String newValue)
    {
        return Boolean.parseBoolean(newValue) != this.defaultValueBoolean;
    }

    @Override
    public void resetToDefault()
    {
        this.valueBoolean = this.defaultValueBoolean;
    }

    @Override
    public JsonElement getAsJsonElement()
    {
        return new JsonPrimitive(this.valueBoolean);
    }

    @Override
    public void setValueFromJsonElement(JsonElement element)
    {
        try
        {
            if (element.isJsonPrimitive())
            {
                this.valueBoolean = element.getAsBoolean();
            }
            else
            {
                Tweakeroo.logger.warn("Failed to set config value for '{}' from the JSON element '{}'", this.getName(), element);
            }
        }
        catch (Exception e)
        {
            Tweakeroo.logger.warn("Failed to set config value for '{}' from the JSON element '{}'", this.getName(), element, e);
        }
    }
}
