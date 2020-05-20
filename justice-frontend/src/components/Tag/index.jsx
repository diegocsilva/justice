import React from 'react';
import TagStyled from './styled';

export const Tag = (props) => {
  const { name, id, tags, onAdd, onDelete, variant } = props;

  const handleDelete = (i) => {
    tags = tags.filter((tag, index) => index !== i);
  };

  const handleAddition = (tag) => {
    tags = [...tags, tag];
  };

  return (
    <TagStyled
      id={id}
      name={name}
      value={tags}
      variant={'outlined'}
      helperText={'Tags'}
      fullWidth={true}
      alwaysShowPlaceholder={true}
      onAdd={(tag) => onAdd(tag)}
      onDelete={(tag, index) => onDelete(tag, index)}
      newChipKeys={['ENTER', 'SPACE']}
    />
  );
};
