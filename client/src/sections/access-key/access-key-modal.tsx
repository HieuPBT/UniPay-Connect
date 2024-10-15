'use client'

import { Box, Button, Modal, TextField, Typography, useTheme } from "@mui/material";
import { IAccessKeyModel } from "./types/access-key";

interface Props {
    open: boolean;
    handleClose: any;
    handleChange: any;
    handleSubmit: any;
    data: IAccessKeyModel;
}

export default function AccessKeyModal({ open, handleClose, handleChange, handleSubmit, data }: Props) {
    const theme = useTheme();
    return (
        <Modal open={open} onClose={handleClose}>
            <Box sx={{
                position: 'absolute',
                [theme.breakpoints.up('md')]: {
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)',
                },
                [theme.breakpoints.down('md')]: {
                    margin: 3
                },
                overflowY: 'auto',
                maxHeight: '90vh',
                bgcolor: 'background.paper',
                boxShadow: 24,
                p: 4,
                borderRadius: 2
            }}>
                <Typography>Edit Access Key</Typography>
                {/* <TextField
                    label="status"
                    name="status"
                    fullWidth
                    margin="normal"
                    value={agent.code}
                    onChange={handleChange}
                /> */}

                <Box sx={{ display: 'flex', justifyContent: 'flex-end', mt: 2 }}>
                    <Button onClick={handleClose} sx={{ mr: 2 }}>Cancel</Button>
                    <Button variant="contained" color="primary" onClick={handleSubmit}>Submit</Button>
                </Box>
            </Box>
        </Modal>
    )
}