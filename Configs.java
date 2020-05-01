package fi.dy.masa.tweakeroo.config;

import java.io.File;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.HudAlignment;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.IHotkeyTogglable;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigColor;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import fi.dy.masa.malilib.util.ActiveMode;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;
import fi.dy.masa.malilib.util.restrictions.UsageRestriction.ListType;
import fi.dy.masa.tweakeroo.Reference;
import fi.dy.masa.tweakeroo.tweaks.MiscTweaks;
import fi.dy.masa.tweakeroo.tweaks.PlacementTweaks;
import fi.dy.masa.tweakeroo.util.InventoryUtils;
import fi.dy.masa.tweakeroo.util.PlacementRestrictionMode;
import fi.dy.masa.tweakeroo.util.SnapAimMode;

public class Configs implements IConfigHandler
{
    private static final String CONFIG_FILE_NAME = Reference.MOD_ID + ".json";

    public static class Generic
    {
        public static final ConfigInteger       AFTER_CLICKER_CLICK_COUNT           = new ConfigInteger     ("放置後右鍵次數",  1, 1, 32, "Tweakeroo 切換 -> 放置後右鍵 功能配置");
        public static final ConfigDouble        BLOCK_REACH_DISTANCE                = new ConfigDouble      ("最大放置距離上限", 4.5, 0, 8, "Tweakeroo 切換 -> 調整最大放置距離 功能設定");
        public static final ConfigInteger       BREAKING_GRID_SIZE                  = new ConfigInteger     ("網格破壞模式大小", 3, 1, 1000, "Tweakeroo 切換 -> 網格破壞模式 功能設定\n按住設定的熱鍵+滑動滾輪\n即可快速調整該值");
        public static final ConfigOptionList    BREAKING_RESTRICTION_MODE           = new ConfigOptionList  ("方塊破壞限制模式設定", PlacementRestrictionMode.LINE, "Tweakeroo 切換 -> 方塊破壞限制模式 功能設定\n選擇你要使用的方塊破壞限制模式（熱鍵可選）");
        public static final ConfigColor         CHAT_BACKGROUND_COLOR               = new ConfigColor       ("聊天訊息顏色", "#80000000", "Tweakeroo 切換 -> 覆蓋預設聊天訊息顏色 功能設定\n在此處設定你想要的顏色");
        public static final ConfigString        CHAT_TIME_FORMAT                    = new ConfigString      ("時間標記格式設定", "[HH:mm:ss]", "Tweakeroo 切換 -> 聊天資訊時間標記 相關設定\n你可以做出一些你需要的更改");
        public static final ConfigBoolean       CLIENT_PLACEMENT_ROTATION           = new ConfigBoolean     ("單人遊戲和客戶端放置輪換", true, "啟用單人遊戲和客戶端放置輪換，例如在沒有Carpet mod的單人遊戲中進行精確放置");
        public static final ConfigOptionList    ELYTRA_CAMERA_INDICATOR             = new ConfigOptionList  ("視角旋轉相關設定", ActiveMode.WITH_KEY, "在視角旋轉處於啟用狀態時渲染真實的俯仰角指示器");
        public static final ConfigInteger       FAST_BLOCK_PLACEMENT_COUNT          = new ConfigInteger     ("fastBlockPlacementCount快速方塊放置", 2, 1, 16, "Tweakeroo 切換 -> 快速放置模式 功能設定\n每個遊戲刻（tick)可放置的最大方塊數");
        public static final ConfigInteger       FAST_LEFT_CLICK_COUNT               = new ConfigInteger     ("快速左鍵次數",  10, 1, 64, "每個遊戲刻(tick)左鍵次數\nTweakeroo 切換 -> 快速左鍵單擊 功能配置");
        public static final ConfigInteger       FAST_RIGHT_CLICK_COUNT              = new ConfigInteger     ("快速右鍵次數", 10, 1, 64, "每個遊戲刻(tick)右鍵次數\nTweakeroo 切換 -> 快速右鍵單擊 功能配置");
        public static final ConfigInteger       FILL_CLONE_LIMIT                    = new ConfigInteger     ("fill/clone上限", 10000000, 1, 1000000000, "Tweakeroo 切換 -> 更改fill/clone方塊上限 功能配置\n在單人模式更改fill/clone方塊上限數量");
        public static final ConfigColor         FLEXIBLE_PLACEMENT_OVERLAY_COLOR    = new ConfigColor       ("疊加方塊中目前指向區域顏色", "#C03030F0", "疊加塊中目前指向區域的顏色設定");
        public static final ConfigDouble        FLY_SPEED_PRESET_1                  = new ConfigDouble      ("飛行速度預設1", 0.01, 0, 4, "通用熱鍵 -> 飛行速度 功能預設 1");
        public static final ConfigDouble        FLY_SPEED_PRESET_2                  = new ConfigDouble      ("飛行速度預設2", 0.064, 0, 4, "通用熱鍵 -> 飛行速度 功能預設 2");
        public static final ConfigDouble        FLY_SPEED_PRESET_3                  = new ConfigDouble      ("飛行速度預設3", 0.128, 0, 4, "通用熱鍵 -> 飛行速度 功能預設 3");
        public static final ConfigDouble        FLY_SPEED_PRESET_4                  = new ConfigDouble      ("飛行速度預設4", 0.32, 0, 4, "通用熱鍵 -> 飛行速度 功能預設 4");
        public static final ConfigBoolean       FREE_CAMERA_MOTION_TOGGLE           = new ConfigBoolean     ("靈魂出竅相關設定", true, "如果啟用，則切換開/關「靈魂出竅」模式也將自動切換開/關「靈魂出竅不影響肉體」功能");
        public static final ConfigInteger       GAMMA_OVERRIDE_VALUE                = new ConfigInteger     ("無限夜視覆蓋使用伽馬值", 16, 0, 1000, "Tweakeroo 切換 -> 無限夜視 功能設定\n在此設定用於覆蓋原伽馬值的新伽馬值");
        public static final ConfigBoolean       HAND_RESTOCK_PRE                    = new ConfigBoolean     ("預先自動補貨", true, "如果啟用，則在一組物品用完之前會進行自動補貨");
        public static final ConfigInteger       HOTBAR_SLOT_CYCLE_MAX               = new ConfigInteger     ("自動切換物品欄最大編號", 2, 1, 9, "Tweakeroo 切換 -> 自動切換物品欄 功能設定\n噹噹前物品欄超過設定的值時候\n會自動跳回第一個物品欄");
        public static final ConfigInteger       HOTBAR_SLOT_RANDOMIZER_MAX          = new ConfigInteger     ("隨機切換物品欄最大編號", 5, 1, 9, "Tweakeroo 切換 -> 隨機切換物品欄 功能設定在使用物品後從1到最大編號隨機選中一個物品欄");
        public static final ConfigOptionList    HOTBAR_SWAP_OVERLAY_ALIGNMENT       = new ConfigOptionList  ("hotbarSwapOverlayAlignment熱鍵交換", HudAlignment.BOTTOM_RIGHT, "物品欄交換覆蓋的位置\n【Tweakeroo 熱鍵->熱鍵交換 相關設定】");
        public static final ConfigInteger       HOTBAR_SWAP_OVERLAY_OFFSET_X        = new ConfigInteger     ("hotbarSwapOverlayOffsetX熱鍵交換", 4, "The horizontal offset of the hotbar swap overlay\n【Tweakeroo 熱鍵->熱鍵交換 相關設定】");
        public static final ConfigInteger       HOTBAR_SWAP_OVERLAY_OFFSET_Y        = new ConfigInteger     ("hotbarSwapOverlayOffsetY熱鍵交換", 4, "The vertical offset of the hotbar swap overlay\n【Tweakeroo 熱鍵->熱鍵交換 相關設定】");
        public static final ConfigInteger       ITEM_SWAP_DURABILITY_THRESHOLD      = new ConfigInteger     ("itemSwapDurabilityThreshold物品交換", 20, 0, 10000, "會檢測手中物品的耐久值\n物品耐久值低於5%時替換物品\n【Tweakeroo 熱鍵->熱鍵交換 相關設定】");
        public static final ConfigBoolean       LAVA_VISIBILITY_OPTIFINE            = new ConfigBoolean     ("lavaVisibilityOptifineCompat岩漿夜視設定", true, "Tweakeroo 切換 -> 岩漿夜視 的替代版本\n它與Optifine相容\n（但有更多程式碼上的技巧)Nessie的功勞.");
        public static final ConfigInteger       MAP_PREVIEW_SIZE                    = new ConfigInteger     ("mapPreviewSize地圖預覽大小", 160, 16, 512, "Tweakeroo 切換 -> 地圖預覽 功能設定\n預覽地圖的渲染大小");
        public static final ConfigInteger       PERIODIC_ATTACK_INTERVAL            = new ConfigInteger     ("periodicAttackInterval定時攻擊設定", 20, 0, Integer.MAX_VALUE, "Tweakeroo 切換 -> 定時攻擊 功能設定\n在此設定隔多少遊戲刻（tick)攻擊一次\n注意>1 tick=0.05s");
        public static final ConfigInteger       PERIODIC_USE_INTERVAL               = new ConfigInteger     ("periodicUseInterval定時使用設定", 20, 0, Integer.MAX_VALUE, "Tweakeroo 切換 -> 定時攻擊 功能設定\n在此設定隔多少遊戲刻（tick)使用一次\n注意>1 tick=0.05s");
        public static final ConfigBoolean       PERMANENT_SNEAK_ALLOW_IN_GUIS       = new ConfigBoolean     ("permanentSneakAllowInGUIs", false, "如果為true，則在打開GUI時，永久性的臨時調整也將起作用");
        public static final ConfigInteger       PLACEMENT_GRID_SIZE                 = new ConfigInteger     ("placementGridSize", 3, 1, 1000, "The grid interval size for the grid placement mode.\nTo quickly adjust the value, scroll while\nholding down the tweak toggle keybind.");
        public static final ConfigInteger       PLACEMENT_LIMIT                     = new ConfigInteger     ("placementLimit", 3, 1, 10000, "The number of blocks you are able to place at maximum per\nright click, if tweakPlacementLimit is enabled.\nTo quickly adjust the value, scroll while\nholding down the tweak toggle keybind.");
        public static final ConfigOptionList    PLACEMENT_RESTRICTION_MODE          = new ConfigOptionList  ("placementRestrictionMode", PlacementRestrictionMode.FACE, "要使用的展示位置限制模式（熱鍵可選）");
        public static final ConfigBoolean       PLACEMENT_RESTRICTION_TIED_TO_FAST  = new ConfigBoolean     ("placementRestrictionTiedToFast", true, "啟用後，當您切換「快速放置」模式時，「放置限制」模式將切換開啟/關閉狀態。");
        public static final ConfigBoolean       POTION_WARNING_BENEFICIAL_ONLY      = new ConfigBoolean     ("僅警告正面藥水效果消失", true, "僅警告正面藥水效果用盡");
        public static final ConfigInteger       POTION_WARNING_THRESHOLD            = new ConfigInteger     ("藥水效果警告值", 600, 1, 1000000, "Tweakeroo 切換 -> 藥水效果耗盡警告\n當藥水效果的剩餘持續時間（以tick為單位）到設定值後將開始顯示警告");
        public static final ConfigInteger       RENDER_LIMIT_ITEM                   = new ConfigInteger     ("最大物品渲染數", -1, -1, 10000, "Tweakeroo 切換 -> 限制每幀實體數 功能設定\n限制每幀渲染的物品實體的最大數量.\n請使用-1以禁用此限制。");
        public static final ConfigInteger       RENDER_LIMIT_XP_ORB                 = new ConfigInteger     ("最大經驗球渲染數", -1, -1, 10000, "Tweakeroo 切換 -> 限制每幀實體數 功能設定\n限制每幀渲染的經驗球實體的最大數量.\n請使用-1以禁用此限制");
        public static final ConfigBoolean       SHULKER_DISPLAY_BACKGROUND_COLOR    = new ConfigBoolean     ("界伏盒預覽顏色", true, "使用盒子的染料顏色為界伏盒預覽背景/紋理著色");
        public static final ConfigBoolean       SHULKER_DISPLAY_REQUIRE_SHIFT       = new ConfigBoolean     ("界伏盒預覽需要按住shift", true, "界伏盒預覽是否需要保持按住");
        public static final ConfigBoolean       SLOT_SYNC_WORKAROUND                = new ConfigBoolean     ("slotSyncWorkaround", true, "樣可以防止伺服器在快速使用的專案（例如快速）上覆蓋永續性或堆疊大小");
        public static final ConfigBoolean       SNAP_AIM_INDICATOR                  = new ConfigBoolean     ("snapAimIndicator渲染捕捉瞄準指示器", true, "否渲染捕捉瞄準角指示器");
        public static final ConfigColor         SNAP_AIM_INDICATOR_COLOR            = new ConfigColor       ("snapAimIndicatorColor對齊目標指示器背景的顏色", "#603030FF", "對齊目標指示器背景的顏色");
        public static final ConfigOptionList    SNAP_AIM_MODE                       = new ConfigOptionList  ("snapAimMode快速對齊模式", SnapAimMode.YAW, "快速對齊模式：偏航或俯仰，或兩者");
        public static final ConfigBoolean       SNAP_AIM_PITCH_OVERSHOOT            = new ConfigBoolean     ("snapAimPitchOvershoot", false, "Whether or not to allow overshooting the pitch angle\nfrom the normal +/- 90 degrees up to +/- 180 degrees");
        public static final ConfigDouble        SNAP_AIM_PITCH_STEP                 = new ConfigDouble      ("snapAimPitchStep", 12.5, 0, 90, "The pitch angle step of the snap aim tweak");
        public static final ConfigDouble        SNAP_AIM_YAW_STEP                   = new ConfigDouble      ("snapAimYawStep", 45, 0, 360, "The yaw angle step of the snap aim tweak");
        public static final ConfigInteger       STRUCTURE_BLOCK_MAX_SIZE            = new ConfigInteger     ("structureBlockMaxSize結構方塊最大儲存上限", 128, 1, 256, "結構方塊最大儲存上限");
        public static final ConfigDouble        ZOOM_FOV                            = new ConfigDouble      ("zoomFov", 30, 0, 600, "縮放功能使用的FOV值");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                CLIENT_PLACEMENT_ROTATION,
                FREE_CAMERA_MOTION_TOGGLE,
                LAVA_VISIBILITY_OPTIFINE,
                HAND_RESTOCK_PRE,
                PERMANENT_SNEAK_ALLOW_IN_GUIS,
                PLACEMENT_RESTRICTION_TIED_TO_FAST,
                POTION_WARNING_BENEFICIAL_ONLY,
                SHULKER_DISPLAY_BACKGROUND_COLOR,
                SHULKER_DISPLAY_REQUIRE_SHIFT,
                SLOT_SYNC_WORKAROUND,
                SNAP_AIM_INDICATOR,
                SNAP_AIM_PITCH_OVERSHOOT,

                BREAKING_RESTRICTION_MODE,
                ELYTRA_CAMERA_INDICATOR,
                PLACEMENT_RESTRICTION_MODE,
                HOTBAR_SWAP_OVERLAY_ALIGNMENT,
                SNAP_AIM_MODE,

                CHAT_TIME_FORMAT,
                CHAT_BACKGROUND_COLOR,
                FLEXIBLE_PLACEMENT_OVERLAY_COLOR,
                SNAP_AIM_INDICATOR_COLOR,

                AFTER_CLICKER_CLICK_COUNT,
                BLOCK_REACH_DISTANCE,
                BREAKING_GRID_SIZE,
                FAST_BLOCK_PLACEMENT_COUNT,
                FAST_LEFT_CLICK_COUNT,
                FAST_RIGHT_CLICK_COUNT,
                FILL_CLONE_LIMIT,
                FLY_SPEED_PRESET_1,
                FLY_SPEED_PRESET_2,
                FLY_SPEED_PRESET_3,
                FLY_SPEED_PRESET_4,
                GAMMA_OVERRIDE_VALUE,
                HOTBAR_SLOT_CYCLE_MAX,
                HOTBAR_SLOT_RANDOMIZER_MAX,
                HOTBAR_SWAP_OVERLAY_OFFSET_X,
                HOTBAR_SWAP_OVERLAY_OFFSET_Y,
                ITEM_SWAP_DURABILITY_THRESHOLD,
                MAP_PREVIEW_SIZE,
                PERIODIC_ATTACK_INTERVAL,
                PERIODIC_USE_INTERVAL,
                PLACEMENT_GRID_SIZE,
                PLACEMENT_LIMIT,
                POTION_WARNING_THRESHOLD,
                RENDER_LIMIT_ITEM,
                RENDER_LIMIT_XP_ORB,
                SNAP_AIM_PITCH_STEP,
                SNAP_AIM_YAW_STEP,
                STRUCTURE_BLOCK_MAX_SIZE,
                ZOOM_FOV
        );
    }

    public static class Fixes
    {
        public static final ConfigBoolean CLIENT_CHUNK_ENTITY_DUPE          = new ConfigBoolean("clientChunkEntityDupeFix客戶端實體欺騙修復", false, "修復當接受區塊數據包時，客戶端實際上覆制了區塊中的實體的bug");
        public static final ConfigBoolean ELYTRA_FIX                        = new ConfigBoolean("elytraFix鞘翅修復", false, "Earthcomputer和Nessie對鞘翅使用/落地的修復");
        public static final ConfigBoolean PROFILER_CHART_FIX                = new ConfigBoolean("profilerChartFix除錯探查器修復", false, "新增了針對除錯探查器的修復程式，該修復程式在MC 1.14.4中被損壞");
        public static final ConfigBoolean RAVAGER_CLIENT_BLOCK_BREAK_FIX    = new ConfigBoolean("ravagerClientBlockBreakFix掠奪者破壞方塊修復", false, "修復了掠奪者破壞客戶端方塊的問題，\n這會導致令人討厭的幽靈方塊/方塊不同步");
        public static final ConfigBoolean TILE_UNLOAD_OPTIMIZATION          = new ConfigBoolean("tileEntityUnloadOptimization方塊實體加/解除安裝修復", false, "優化從>>世界<<列表中刪除正在解除安裝的方塊實體的操作.\n如果一次載入和/或解除安裝很多方塊實體的話,\n可以大大提高效能.");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                CLIENT_CHUNK_ENTITY_DUPE,
                ELYTRA_FIX,
                PROFILER_CHART_FIX,
                RAVAGER_CLIENT_BLOCK_BREAK_FIX,
                TILE_UNLOAD_OPTIMIZATION
        );
    }

    public static class Lists
    {
        public static final ConfigOptionList FAST_PLACEMENT_ITEM_LIST_TYPE      = new ConfigOptionList("fastPlacementItemListType快速放置模式-限制類型", ListType.BLACKLIST, "快速放置模式的限制類型\nblacklist=禁用黑名單內物品\nwhitelist=僅可用白名單內物品");
        public static final ConfigStringList FAST_PLACEMENT_ITEM_BLACKLIST      = new ConfigStringList("fastPlacementItemBlackList快速放置模式-黑名單", ImmutableList.of("minecraft:ender_chest", "minecraft:white_shulker_box"), "如果快速放置模式限制類型設定為BlackList(黑名單），則不允許將這些專案用於快速放置模式");
        public static final ConfigStringList FAST_PLACEMENT_ITEM_WHITELIST      = new ConfigStringList("fastPlacementItemWhiteList快速放置模式-白名單", ImmutableList.of(), "如果快速放置模式限制類型設定為Whitelist(白名單），則僅允許將這些專案用於快速放置模式");
        public static final ConfigOptionList FAST_RIGHT_CLICK_BLOCK_LIST_TYPE   = new ConfigOptionList("fastRightClickBlockListType快速右鍵（方塊）-限制類型", ListType.BLACKLIST, "快速右鍵（方塊）的限制類型\nblacklist=禁用黑名單內物品\nwhitelist=僅可用白名單內物品");
        public static final ConfigStringList FAST_RIGHT_CLICK_BLOCK_BLACKLIST   = new ConfigStringList("fastRightClickBlockBlackList快速右鍵（方塊）-黑名單", ImmutableList.of("minecraft:chest", "minecraft:ender_chest", "minecraft:trapped_chest", "minecraft:white_shulker_box"), "如果快速右鍵模式限制類型設定為BlackList(黑名單），則不允許將這些專案用於快速右鍵");
        public static final ConfigStringList FAST_RIGHT_CLICK_BLOCK_WHITELIST   = new ConfigStringList("fastRightClickBlockWhiteList快速右鍵（方塊）-白名單", ImmutableList.of(), "如果快速放置模式限制類型設定為WhiteList(白名單），則僅允許將這些專案用於快速右鍵");
        public static final ConfigOptionList FAST_RIGHT_CLICK_ITEM_LIST_TYPE    = new ConfigOptionList("fastRightClickListType快速右鍵（物品）-限制類型", ListType.NONE, "快速右鍵（物品）的限制類型\nblacklist=禁用黑名單內物品\nwhitelist=僅可用白名單內物品");
        public static final ConfigStringList FAST_RIGHT_CLICK_ITEM_BLACKLIST    = new ConfigStringList("fastRightClickBlackList快速右鍵（物品）-黑名單", ImmutableList.of("minecraft:fireworks"), "如果快速右鍵-限制類型設定為BlackList(黑名單），則不允許將這些物品用於快速右鍵");
        public static final ConfigStringList FAST_RIGHT_CLICK_ITEM_WHITELIST    = new ConfigStringList("fastRightClickWhiteList快速右鍵（物品）-白名單", ImmutableList.of("minecraft:bucket", "minecraft:water_bucket", "minecraft:lava_bucket", "minecraft:glass_bottle"), "如果快速右鍵-限制類型設定為WhiteList(白名單），則僅允許將這些物品用於快速右鍵");
        public static final ConfigStringList FLAT_WORLD_PRESETS                 = new ConfigStringList("flatWorldPresets", ImmutableList.of("White Glass;1*minecraft:white_stained_glass;minecraft:plains;;minecraft:white_stained_glass", "Glass;1*minecraft:glass;minecraft:plains;;minecraft:glass"), "Custom flat world preset strings.\nThese are in the format: name;blocks_string;biome;generation_features;icon_item\nThe blocks string format is the vanilla format, such as: 62*minecraft:dirt,minecraft:grass\nThe biome can be the registry name, or the int ID\nThe icon item name format is minecraft:iron_nugget");
        public static final ConfigOptionList POTION_WARNING_LIST_TYPE           = new ConfigOptionList("potionWarningListType", ListType.NONE, "The list type for potion warning effects");
        public static final ConfigStringList POTION_WARNING_BLACKLIST           = new ConfigStringList("potionWarningBlackList", ImmutableList.of("minecraft:hunger", "minecraft:mining_fatigue", "minecraft:nausea", "minecraft:poison", "minecraft:slowness", "minecraft:weakness"), "The potion effects that will not be warned about");
        public static final ConfigStringList POTION_WARNING_WHITELIST           = new ConfigStringList("potionWarningWhiteList", ImmutableList.of("minecraft:fire_resistance", "minecraft:invisibility", "minecraft:water_breathing"), "The only potion effects that will be warned about");
        public static final ConfigStringList REPAIR_MODE_SLOTS                  = new ConfigStringList("repairModeSlots", ImmutableList.of("mainhand", "offhand"), "The slots the repair mode should use\nValid values: mainhand, offhand, head, chest, legs, feet");
        public static final ConfigStringList UNSTACKING_ITEMS                   = new ConfigStringList("unstackingItems", ImmutableList.of("minecraft:bucket", "minecraft:glass_bottle"), "The items that should be considered for the\n'tweakItemUnstackingProtection' tweak");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                FAST_PLACEMENT_ITEM_LIST_TYPE,
                FAST_RIGHT_CLICK_BLOCK_LIST_TYPE,
                FAST_RIGHT_CLICK_ITEM_LIST_TYPE,
                POTION_WARNING_LIST_TYPE,
                FAST_PLACEMENT_ITEM_BLACKLIST,
                FAST_PLACEMENT_ITEM_WHITELIST,
                FAST_RIGHT_CLICK_BLOCK_BLACKLIST,
                FAST_RIGHT_CLICK_BLOCK_WHITELIST,
                FAST_RIGHT_CLICK_ITEM_BLACKLIST,
                FAST_RIGHT_CLICK_ITEM_WHITELIST,
                FLAT_WORLD_PRESETS,
                POTION_WARNING_BLACKLIST,
                POTION_WARNING_WHITELIST,
                REPAIR_MODE_SLOTS,
                UNSTACKING_ITEMS
        );
    }

    public static class Disable
    {
        public static final ConfigBooleanHotkeyed       DISABLE_BLOCK_BREAK_PARTICLES   = new ConfigBooleanHotkeyed("disableBlockBreakingParticles關閉破壞效果",        false, "", "關閉方塊破壞粒子效果\n(這最初來自於nessie的有用的mod)");
        public static final ConfigBooleanHotkeyed       DISABLE_DOUBLE_TAP_SPRINT       = new ConfigBooleanHotkeyed("disableDoubleTapSprint禁用雙擊快速鍵加速",               false, "", "禁用雙擊快速鍵加速");
        public static final ConfigBooleanHotkeyed       DISABLE_BOSS_FOG                = new ConfigBooleanHotkeyed("disableBossFog消除BOSS造成的粒子效果",                       false, "", "消除BOSS造成的粒子效果");
        public static final ConfigBooleanHotkeyed       DISABLE_CLIENT_ENTITY_UPDATES   = new ConfigBooleanHotkeyed("disableClientEntityUpdates關閉客戶端實體更新",           false, "", "關閉除玩家外一切實體更新\n這可能可以解決你在一些實體過多或者\n有較多實體情況下中出現FPS降低的問題");
        public static final ConfigBooleanHotkeyed       DISABLE_DEAD_MOB_RENDERING      = new ConfigBooleanHotkeyed("disableDeadMobRendering遮蔽死亡後變紅實體",              false, "", "遮蔽死後變紅的實體 (此時實體的生命為0)");
        public static final ConfigBooleanHotkeyed       DISABLE_DEAD_MOB_TARGETING      = new ConfigBooleanHotkeyed("disableDeadMobTargeting準星穿透死亡動畫",              false, "", "準星會透過死亡動畫裡面的生物\n防止你鞭屍");
        public static final ConfigBooleanHotkeyed       DISABLE_ENTITY_RENDERING        = new ConfigBooleanHotkeyed("disableEntityRendering禁用客戶端實體渲染",               false, "", "禁用除玩家外的所有實體渲染\n如果世界上有大量的實體可以幫助解決問題");
        public static final ConfigBooleanHotkeyed       DISABLE_ENTITY_TICKING          = new ConfigBooleanClient  ("disableEntityTicking禁用客戶端實體運算",                 false, "", "不運算除了玩家以外所有實體");
        public static final ConfigBooleanHotkeyed       DISABLE_FALLING_BLOCK_RENDER    = new ConfigBooleanHotkeyed("disableFallingBlockEntityRendering禁用掉落方塊渲染",   false, "", "禁用掉落的方塊的實體渲染（有助於提高混凝土工廠FPS）");
        public static final ConfigBooleanHotkeyed       DISABLE_INVENTORY_EFFECTS       = new ConfigBooleanHotkeyed("disableInventoryEffectRendering禁用藥水效果渲染",      false, "", "從GUI移除藥水效果渲染");
        public static final ConfigBooleanHotkeyed       DISABLE_ITEM_SWITCH_COOLDOWN    = new ConfigBooleanHotkeyed("disableItemSwitchRenderCooldown禁止快捷欄切換冷卻",      false, "", "如果啟用，則在切換快捷欄時不會有任何冷卻/裝備動畫");
        public static final ConfigBooleanHotkeyed       DISABLE_MOB_SPAWNER_MOB_RENDER  = new ConfigBooleanHotkeyed("disableMobSpawnerMobRendering禁止怪物產生器中實體渲染",        false, "", "從怪物產生器（如刷怪籠）中移除實體渲染");
        public static final ConfigBooleanHotkeyed       DISABLE_NETHER_FOG              = new ConfigBooleanHotkeyed("disableNetherFog消除地獄紅色霧氣",                     false, "", "消除地獄的霧氣（就是往遠處看不會是一片紅色的）");
        public static final ConfigBooleanHotkeyed       DISABLE_OBSERVER                = new ConfigBooleanClient  ("disableObserver禁止偵測器被觸發",                      false, "", "禁止偵測器被觸發");
        public static final ConfigBooleanHotkeyed       DISABLE_OFFHAND_RENDERING       = new ConfigBooleanHotkeyed("disableOffhandRendering禁用物品實體渲染",              false, "", "關閉物品實體渲染");
        public static final ConfigBooleanHotkeyed       DISABLE_PARTICLES               = new ConfigBooleanHotkeyed("disableParticles關閉所有粒子效果",                     false, "", "關閉所有粒子效果");
        public static final ConfigBooleanHotkeyed       DISABLE_PORTAL_GUI_CLOSING      = new ConfigBooleanHotkeyed("disablePortalGuiClosing禁止在地獄門揹包無法打開",              false, "", "如果開啟，你可以站在地獄門中打開揹包");
        public static final ConfigBooleanHotkeyed       DISABLE_RAIN_EFFECTS            = new ConfigBooleanHotkeyed("disableRainEffects禁用雨水效果及音效",                   false, "", "關閉下雨時雨水效果及音效");
        public static final ConfigBooleanHotkeyed       DISABLE_SIGN_GUI                = new ConfigBooleanHotkeyed("disableSignGui禁用告示牌編輯GUI",                       false, "", "禁止打開告示牌編輯GUI\n在放置大量告示牌時很有用");
        public static final ConfigBooleanHotkeyed       DISABLE_SLIME_BLOCK_SLOWDOWN    = new ConfigBooleanHotkeyed("disableSlimeBlockSlowdown關閉在粘液塊上減速效果",            false, "", "取消在粘液塊上行走時的減速.\n(這是nessie寫的沒用的mod.)");
        public static final ConfigBooleanHotkeyed       DISABLE_TILE_ENTITY_RENDERING   = new ConfigBooleanHotkeyed("disableTileEntityRendering禁用方塊實體渲染",           false, "", "禁止所有方塊實體渲染（如活塞移動等）");
        public static final ConfigBooleanHotkeyed       DISABLE_TILE_ENTITY_TICKING     = new ConfigBooleanClient  ("disableTileEntityTicking禁用方塊實體運算",             false, "", "禁止運算所有方塊實體");
        public static final ConfigBooleanHotkeyed       DISABLE_VILLAGER_TRADE_LOCKING  = new ConfigBooleanClient  ("disableVillagerTradeLocking防止村民交易鎖定",          false, "", "通過始終增加最大使用量來防止村民交易鎖定");
        public static final ConfigBooleanHotkeyed       DISABLE_WALL_UNSPRINT           = new ConfigBooleanHotkeyed("disableWallUnsprint防止碰到墻退出加速模式",                  false, "", "碰到墻壁不讓你退出加速模式");

        public static final ImmutableList<IHotkeyTogglable> OPTIONS = ImmutableList.of(
                DISABLE_BLOCK_BREAK_PARTICLES,
                DISABLE_DOUBLE_TAP_SPRINT,
                DISABLE_BOSS_FOG,
                DISABLE_CLIENT_ENTITY_UPDATES,
                DISABLE_DEAD_MOB_RENDERING,
                DISABLE_DEAD_MOB_TARGETING,
                DISABLE_ENTITY_RENDERING,
                DISABLE_ENTITY_TICKING,
                DISABLE_FALLING_BLOCK_RENDER,
                DISABLE_INVENTORY_EFFECTS,
                DISABLE_ITEM_SWITCH_COOLDOWN,
                DISABLE_MOB_SPAWNER_MOB_RENDER,
                DISABLE_NETHER_FOG,
                DISABLE_OBSERVER,
                DISABLE_OFFHAND_RENDERING,
                DISABLE_PARTICLES,
                DISABLE_PORTAL_GUI_CLOSING,
                DISABLE_RAIN_EFFECTS,
                DISABLE_SIGN_GUI,
                DISABLE_SLIME_BLOCK_SLOWDOWN,
                DISABLE_TILE_ENTITY_RENDERING,
                DISABLE_TILE_ENTITY_TICKING,
                DISABLE_VILLAGER_TRADE_LOCKING,
                DISABLE_WALL_UNSPRINT
        );
    }

    public static class Internal
    {
        public static final ConfigInteger       FLY_SPEED_PRESET                    = new ConfigInteger     ("flySpeedPreset飛行速度預設", 0, 0, 3, "這僅用於Mod內部跟蹤目前選定的飛行速度預設");
        public static final ConfigDouble        GAMMA_VALUE_ORIGINAL                = new ConfigDouble      ("gammaValueOriginal原始伽馬值", 0, 0, 1, "啟用無限夜視之前的原始伽瑪值");
        public static final ConfigInteger       HOTBAR_SCROLL_CURRENT_ROW           = new ConfigInteger     ("hotbarScrollCurrentRow目前快捷欄", 3, 0, 3, "這僅是爲了讓mod內部跟蹤\n >目前快捷欄< 以實現 快捷欄滾動 功能");
        public static final ConfigDouble        SLIME_BLOCK_SLIPPERINESS_ORIGINAL   = new ConfigDouble      ("slimeBlockSlipperinessOriginal粘液塊的原始黏度值", 0.8, 0, 1, "粘液塊的原始滑度值");
        public static final ConfigDouble        SNAP_AIM_LAST_PITCH                 = new ConfigDouble      ("snapAimLastPitch最後捕捉到的音調值", 0, -135, 135, "最後捕捉到的音調值");
        public static final ConfigDouble        SNAP_AIM_LAST_YAW                   = new ConfigDouble      ("snapAimLastYaw最後捕捉到的偏航值", 0, 0, 360, "最後捕捉到的偏航值");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                FLY_SPEED_PRESET,
                GAMMA_VALUE_ORIGINAL,
                HOTBAR_SCROLL_CURRENT_ROW,
                SLIME_BLOCK_SLIPPERINESS_ORIGINAL,
                SNAP_AIM_LAST_YAW
        );
    }

    public static ConfigDouble getActiveFlySpeedConfig()
    {
        switch (Configs.Internal.FLY_SPEED_PRESET.getIntegerValue())
        {
            case 0:  return Configs.Generic.FLY_SPEED_PRESET_1;
            case 1:  return Configs.Generic.FLY_SPEED_PRESET_2;
            case 2:  return Configs.Generic.FLY_SPEED_PRESET_3;
            case 3:  return Configs.Generic.FLY_SPEED_PRESET_4;
            default: return Configs.Generic.FLY_SPEED_PRESET_1;
        }
    }

    public static void loadFromFile()
    {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead())
        {
            JsonElement element = JsonUtils.parseJsonFile(configFile);

            if (element != null && element.isJsonObject())
            {
                JsonObject root = element.getAsJsonObject();

                ConfigUtils.readConfigBase(root, "Fixes", Configs.Fixes.OPTIONS);
                ConfigUtils.readConfigBase(root, "Generic", Configs.Generic.OPTIONS);
                ConfigUtils.readConfigBase(root, "GenericHotkeys", Hotkeys.HOTKEY_LIST);
                ConfigUtils.readConfigBase(root, "Internal", Configs.Internal.OPTIONS);
                ConfigUtils.readConfigBase(root, "Lists", Configs.Lists.OPTIONS);
                ConfigUtils.readHotkeyToggleOptions(root, "DisableHotkeys", "DisableToggles", ImmutableList.copyOf(Disable.OPTIONS));
                ConfigUtils.readHotkeyToggleOptions(root, "TweakHotkeys", "TweakToggles", ImmutableList.copyOf(FeatureToggle.values()));
            }
        }

        InventoryUtils.setRepairModeSlots(Lists.REPAIR_MODE_SLOTS.getStrings());
        InventoryUtils.setUnstackingItems(Lists.UNSTACKING_ITEMS.getStrings());

        PlacementTweaks.FAST_RIGHT_CLICK_BLOCK_RESTRICTION.setListType((ListType) Lists.FAST_RIGHT_CLICK_BLOCK_LIST_TYPE.getOptionListValue());
        PlacementTweaks.FAST_RIGHT_CLICK_BLOCK_RESTRICTION.setListContents(
                Lists.FAST_RIGHT_CLICK_BLOCK_BLACKLIST.getStrings(),
                Lists.FAST_RIGHT_CLICK_BLOCK_WHITELIST.getStrings());

        PlacementTweaks.FAST_RIGHT_CLICK_ITEM_RESTRICTION.setListType((ListType) Lists.FAST_RIGHT_CLICK_ITEM_LIST_TYPE.getOptionListValue());
        PlacementTweaks.FAST_RIGHT_CLICK_ITEM_RESTRICTION.setListContents(
                Lists.FAST_RIGHT_CLICK_ITEM_BLACKLIST.getStrings(),
                Lists.FAST_RIGHT_CLICK_ITEM_WHITELIST.getStrings());

        PlacementTweaks.FAST_PLACEMENT_ITEM_RESTRICTION.setListType((ListType) Lists.FAST_PLACEMENT_ITEM_LIST_TYPE.getOptionListValue());
        PlacementTweaks.FAST_PLACEMENT_ITEM_RESTRICTION.setListContents(
                Lists.FAST_PLACEMENT_ITEM_BLACKLIST.getStrings(),
                Lists.FAST_PLACEMENT_ITEM_WHITELIST.getStrings());

        MiscTweaks.POTION_RESTRICTION.setListType((ListType) Lists.POTION_WARNING_LIST_TYPE.getOptionListValue());
        MiscTweaks.POTION_RESTRICTION.setListContents(
                Lists.POTION_WARNING_BLACKLIST.getStrings(),
                Lists.POTION_WARNING_WHITELIST.getStrings());
    }

    public static void saveToFile()
    {
        File dir = FileUtils.getConfigDirectory();

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs())
        {
            JsonObject root = new JsonObject();

            ConfigUtils.writeConfigBase(root, "Fixes", Configs.Fixes.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Generic", Configs.Generic.OPTIONS);
            ConfigUtils.writeConfigBase(root, "GenericHotkeys", Hotkeys.HOTKEY_LIST);
            ConfigUtils.writeConfigBase(root, "Internal", Configs.Internal.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Lists", Configs.Lists.OPTIONS);
            ConfigUtils.writeHotkeyToggleOptions(root, "DisableHotkeys", "DisableToggles", ImmutableList.copyOf(Disable.OPTIONS));
            ConfigUtils.writeHotkeyToggleOptions(root, "TweakHotkeys", "TweakToggles", ImmutableList.copyOf(FeatureToggle.values()));

            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }

    @Override
    public void load()
    {
        loadFromFile();
    }

    @Override
    public void save()
    {
        saveToFile();
    }
}
