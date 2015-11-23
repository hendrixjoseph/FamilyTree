/*
 *  The MIT License (MIT)
 *
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *
 *  Copyright (c) 2015 Joseph Hendrix
 *
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *
 */

package edu.wright.hendrix11.c3.Axis;

/**
 * @author Joe Hendrix
 */
public class Label
{
    public class Label
    {
        private LabelPosition position;
        private String text;

        public Label(String text)
        {
            this.text = text;
        }

        public Label(String text, LabelPosition position)
        {
            this.text = text;
            this.position = position;
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder("{text:'");
            sb.append(text);
            sb.append("'");

            if ( position != null )
            {
                sb.append(",position:'");
                sb.append(position.toString());
                sb.append("'");
            }

            sb.append("}");

            return sb.toString();
        }
    }

    public enum LabelPosition
    {
        inner_right,
        inner_center,
        inner_left,
        outer_right,
        outer_center,
        outer_left,
        inner_top,
        inner_middle,
        inner_bottom,
        outer_top,
        outer_middle,
        outer_bottom;

        @Override
        public String toString()
        {
            return super.toString().replace('_', '-');
        }
    }
}
