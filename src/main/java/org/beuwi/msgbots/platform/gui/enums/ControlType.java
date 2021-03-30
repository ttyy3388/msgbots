package org.beuwi.msgbots.platform.gui.enums;

// 탭과 같이 유저가 추가한거랑 프로그램에서 추가한거랑 구분하는 용도임
public enum ControlType {
    NORMAL {
        @Override
        public String toString() {
            return "normal";
        }
    },
    SYSTEM {
        @Override
        public String toString() {
            return "system";
        }
    };

    /* public static final PseudoClass NORMAL_PSEUDO_CLASS = PseudoClass.getPseudoClass("normal");
    public static final PseudoClass SYSTEM_PSEUDO_CLASS = PseudoClass.getPseudoClass("system");

    // Property : Control Type Property
    public static void putPseudoClass(Node target, ObjectProperty<ControlType> property)
    {
        property.addListener((observable, oldType, newType) ->
        {
            PseudoClass oldPseudo = oldType.equals(NORMAL) ? NORMAL_PSEUDO_CLASS : SYSTEM_PSEUDO_CLASS;
            PseudoClass newPseudo = newType.equals(SYSTEM) ? SYSTEM_PSEUDO_CLASS : NORMAL_PSEUDO_CLASS;

            if (oldType != null)
            {
                target.pseudoClassStateChanged(oldPseudo, false);
            }

            target.pseudoClassStateChanged(newPseudo, true);
        });
    }
    */
}
