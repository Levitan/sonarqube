/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
import * as React from 'react';
import * as classNames from 'classnames';
import { formatMeasure } from '../../helpers/measures';
import './Rating.css';

interface Props {
  className?: string;
  muted?: boolean;
  small?: boolean;
  value: string | number;
}

export default function Rating({ className, muted = false, small = false, value }: Props) {
  const formatted = formatMeasure(value, 'RATING');
  return (
    <span
      className={classNames(
        'rating',
        'rating-' + formatted,
        { 'rating-small': small, 'rating-muted': muted },
        className
      )}>
      {formatted}
    </span>
  );
}